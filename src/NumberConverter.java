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
 * <p>The <code>NumberConvert</code> is a final class that provide
 * methods for converting numbers in String format to a Java's Primitive
 * Data Type.
 *
 * This class currently support converting numbers in string
 * representation to integer long and integer int values.
 *
 * This class utilized methods from other class in this package and
 * should not be as fast as when calling other classes directly.
 *
 * @author  Khang Hoang Nguyen
 *
 * @since  1.0.0.f
 **/
public final class NumberConverter{
    private NumberConverter(){};
    /**
     * Parse a string of signed decimal value without a floating point
     * value into an integer int value. This method parses input as
     * signed values. This method does not throw error. However, the
     * {@link Base10Util#toInt(String) Base10Util.toInt} method that
     * ultilizes by this method throws error.
     *
     * @param  input
     *         A string to be parsed to an integer int value.
     *
     * @return  An integer int value of the decimal {@code input}
     *          string.
     *
     * @see  Base10Util#toInt(String)
     *
     * @since  1.0.0.f
     **/
    public static int toInt(final String input){
        return Base10Util.toInt(input);
    }

    /**
     * Parse the input string to an integer int value with the defined
     * base number for the conversion. Current support bases are 2, 3,
     * 8, 10, 16. This method parses input as signed values. This
     * method only throw UnsupportedOperationException. However,
     * methods that ultilizes by this method throw errors.
     *
     * @param  input
     *         A string to be parsed to an integer int value.
     *
     * @param  base
     *         An integer int value that defines the number base of
     *         the {@code input} string.
     *
     * @return  An integer int value of the {@code input} string.
     *
     * @throws  UnsupportedOperationException
     *          If the {@code base} is a value that is not supported.
     *
     * @see  Base2Util#toInt(String)
     * @see  Base3Util#toInt(String)
     * @see  Base8Util#toInt(String)
     * @see  Base10Util#toInt(String)
     * @see  Base16Util#toInt(String)
     *
     * @since  1.0.0.f
     **/
    public static int toInt(final String input, final int base){
        switch (base){
            case 2 : return Base2Util.toInt(input);
            case 3 : return Base3Util.toInt(input);
            case 8 : return Base8Util.toInt(input);
            case 10 : return Base10Util.toInt(input);
            case 16 : return Base16Util.toInt(input);
            default: throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        }
    }

    /**
     * Parse a string of decimal value without a floating point value
     * into an integer long value. This method parses input as signed
     * values. This method does not throw error. However, the
     * {@link Base10Util#toLong(String) Base10Util.toLong} method that
     * ultilizes by this method throws error.
     *
     * @param  input
     *         A string to be parsed to an integer long value.
     *
     * @return  An integer long value of the decimal {@code input}
     *          string.
     *
     * @see  Base10Util#toLong(String)
     *
     * @since  1.0.0.f
     **/
    public static long toLong(final String input){
        return Base10Util.toLong(input);
    }

    /**
     * Parse the input string to an integer long value with the defined
     * base number for the conversion. Current support bases are 2, 3,
     * 8, 10, 16. This method parses input as signed values. This method
     * only throws UnsupportedOperationException. However, methods that
     * ultilizes by this method throw errors.
     *
     * @param  input
     *         A string to be parsed to an integer long value.
     *
     * @param  base
     *         An integer int value that defines the number base of
     *         the {@code input} string.
     *
     * @return  An integer long value of the {@code input} string.
     *
     * @throws  UnsupportedOperationException
     *          If the {@code base} is a value that is not supported.
     *
     * @see  Base2Util#toLong(String)
     * @see  Base3Util#toLong(String)
     * @see  Base8Util#toLong(String)
     * @see  Base10Util#toLong(String)
     * @see  Base16Util#toLong(String)
     *
     * @since  1.0.0.f
     **/
    public static long toLong(final String input, final int base){
        switch (base){
            case 2 : return Base2Util.toLong(input);
            case 3 : return Base3Util.toLong(input);
            case 8 : return Base8Util.toLong(input);
            case 10 : return Base10Util.toLong(input);
            case 16 : return Base16Util.toLong(input);
            default: throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        }
    }
}
