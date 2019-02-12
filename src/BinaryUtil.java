package host.fai.lib.faiNumber;
/*
 * Copyright 2019 Khang Hoang Nguyen
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files
 * (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 **/
/**
 * <p>The <code>BinaryUtil</code> class is a final class that provides
 * static methods for converting binary values in string representation
 * as bits to a Java's Primitive Data Type.
 *
 * <p>Currently this class supports converting binary values in string
 * representation to integer int values and integer long values.
 *
 * <p>This class treats binary strings as bit values represented by
 * binary digits and not real numbers. Thus, binary strings parse by
 * this class will be converted to the suppored data types as if they
 * were real bit values represented by binary digits.
 *
 * <p>To treats binary values as signed or unsigned real base 2 numbers
 * use {@link Base2Util Base2Util}.</p>
 *
 * @author  Khang Hoang Nguyen
 *
 * @since  1.0.0.f
 **/
public final class BinaryUtil{
    private BinaryUtil(){};
    /**
     * Parse the {@code input} string as bits represented by binary
     * digits to an integer int value.
     *
     * @param  input
     *         A string to be parsed as binary digits to an interger
     *         int value.
     *
     * @return  An integer int value of the binary {@code input} string.
     *
     * @throws  NumberFormatException
     *          If the {@code input} string contains invalid binary
     *          digits, or if the {@code input} string contains a value
     *          that is beyond 32 bits. Leading zeroes do not count.
     *
     * @throws  EmptyStringException
     *          If the {@code input} string is empty.
     *
     * @since  1.0.0.f
     **/
    public static final int toInt(final String input){
        final int length = input.length();
        if ( length == 0 ) throw new EmptyStringException();
        int start = 0;

        int out = 0, c;
        while ( start < length && input.charAt(start) == '0' ) start++;
        if ( length - start > 32 ) throw new NumberFormatException(input);

        for ( ; start < length; start++){
            c = (input.charAt(start) ^ '0');
            if ( c > 1 ) throw new NumberFormatException(input);
            out = (out << 1) | c;
        }

        return out;
    }

    /**
     * Parse the {@code input} string as bits represented by binary
     * digits to an integer long value.
     *
     * @param  input
     *         A string to be parsed as binary digits to an interger
     *         long value.
     *
     * @return  An integer long value of the binary {@code input}
     *          string.
     *
     * @throws  NumberFormatException
     *          If the {@code input} string contains invalid binary
     *          digits, or if the {@code input} string contains a value
     *          that is beyond 64 bits. Leading zeroes do not count.
     *
     * @throws  EmptyStringException
     *          If the {@code input} string is empty.
     *
     * @since  1.0.0.f
     **/
    public static final long toLong(final String input){
        final int length = input.length();
        if ( length == 0 ) throw new EmptyStringException();
        int start = 0;

        long out = 0, c;
        while ( start < length && input.charAt(start) == '0' ) start++;
        if ( length - start > 64 ) throw new NumberFormatException(input);

        for ( ; start < length; start++){
            c = (input.charAt(start) ^ '0');
            if ( c > 1L ) throw new NumberFormatException(input);
            out = (out << 1) | c;
        }

        return out;
    }
}
