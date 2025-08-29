package gay.vzt.oss.lavalink.plugin.trackEncoder;

import com.sedmelluq.discord.lavaplayer.tools.DataFormatTools;

import java.io.DataOutput;
import java.io.IOException;

public enum FieldType {
    NULLABLE_TEXT(true),
    TEXT(false),
    LONG(false),
    FLOAT(false),
    DOUBLE(false),
    BOOLEAN(false);

    public final boolean isNullable;

    FieldType(boolean isNullable) {
        this.isNullable = isNullable;
    }

    public void write(DataOutput output, Object value) throws IOException {
        switch (this) {
            case TEXT -> output.writeUTF(value.toString());
            case LONG -> {
                if (value instanceof Number n) {
                    output.writeDouble(n.longValue());
                } else {
                    throw new IllegalArgumentException("value is not a double");
                }
            }
            case FLOAT -> {
                if (value instanceof Number n) {
                    output.writeFloat(n.floatValue());
                } else {
                    throw new IllegalArgumentException("value is not a float");
                }
            }
            case DOUBLE -> {
                if (value instanceof Number n) {
                    output.writeDouble(n.doubleValue());
                } else {
                    throw new IllegalArgumentException("value is not a double");
                }
            }
            case BOOLEAN -> output.writeBoolean((boolean) value);
            case NULLABLE_TEXT -> DataFormatTools.writeNullableText(output, value == null ? null : value.toString());
        }
    }
}
