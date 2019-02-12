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
 * <p>The <code>IntUtil</code> class is a final class that provides
 * static methods for working with integer int values.
 *
 * <p>This class provides methods for parsing integer int values to
 * signed hexadecimal or signed octal digits in string representation.
 *
 * <p>This class supports parsing int values as unsigned int to
 * string of decimal digits.</p>
 *
 * @author  Khang Hoang Nguyen
 *
 * @since  1.0.0.f
 **/
public final class IntUtil{
    private IntUtil(){};

    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                          'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * Check if the input is an odd number.
     *
     * @param  input
     *         An int integer.
     *
     * @return  A boolean value of true if the input is an odd number
     *          or false if it is not.
     *
     * @since  1.0.0.f
     **/
    public static boolean isOdd(final int input){
        return ( (input & 1) == 1 );
    }

    /**
     * Check if the input is an even number.
     *
     * @param  input
     *         An int integer.
     *
     * @return  A boolean value of true if the input value is an even
     *          number or false if it is not.
     *
     * @since  1.0.0.f
     **/
    public static boolean isEven(final int input){
        return ( (input & 1) == 0 );
    }

    /**
     * Parse the integer int {@code input} to a string of signed binary
     * digits.
     *
     * @param  input
     *         An int integer.
     *
     * @return  A string of signed binary digits of the {@code input}'s
     *          value.
     *
     * @since  1.0.0.f
     **/
    public static String toSignedBinary(int input){
        final char[] out = new char[32]; int pos = 32;
        if ( input < 0 ){
            if ( input == -2147483648 ) return "-10000000000000000000000000000000";
            input = ~input + 1;
            while ( input != 0 ){
                out[--pos] = (char)((input & 1) ^ 48);
                input = input >> 1;
            }
            out[--pos] = '-';
        } else if ( input > 0 ) {
            while ( input != 0 ){
                out[--pos] = (char)((input & 1) ^ 48);
                input = input >> 1;
            }
        } else {
            return "0";
        }

        return new String(out, pos, 32 - pos);
    }

    /**
     * Parse the integer int {@code input} to a string of signed octal
     * digits.
     *
     * @param  input
     *         An int integer.
     *
     * @return  A string of signed octal digits of the {@code input}'s
     *          value.
     *
     * @since  1.0.0.f
     **/
    public static String toSignedOctal(int input){
        final char[] out = new char[12]; int pos = 12;
        if ( input < 0 ){
            if ( input == -2147483648 ) return "-20000000000";
            input = ~input + 1;
            while ( input != 0 ){
                out[--pos] = (char)((input & 7) ^ 48);
                input = input >> 3;
            }
            out[--pos] = '-';
        } else if ( input > 0 ) {
            while ( input != 0 ){
                out[--pos] = (char)((input & 7) ^ 48);
                input = input >> 3;
            }
        } else {
            return "0";
        }

        return new String(out, pos, 12 - pos);
    }

    /**
     * Parse the integer int {@code input} to a string of signed
     * hexadecimal digits.
     *
     * @param  input
     *         An int integer.
     *
     * @return  A string of signed hexadecimal digits of the
     *          {@code input}'s value.
     *
     * @since  1.0.0.f
     **/
    public static String toSignedHex(int input){
        final char[] out = new char[9]; int pos = 9;
        if ( input < 0 ){
            if ( input == -2147483648 ) return "-80000000";
            input = ~input + 1;
            while ( input != 0 ){
                out[--pos] = digits[input & 15];
                input = input >> 4;
            }
            out[--pos] = '-';
        } else if ( input > 0) {
            while ( input != 0 ){
                out[--pos] = digits[input & 15];
                input = input >> 4;
            }
        } else {
            return "0";
        }

        return new String(out, pos, 9 - pos);
    }

    /**
     * Parse the integer int {@code input} to a string of decimal as
     * unsigned bits.
     *
     * @param  input
     *         An int integer.
     *
     * @return  A string of decimal of the {@code input}'s value as
     *          unsigned bits.
     *
     * @since  1.0.0.f
     **/
    public static String toStringAsUnsigned(final int input){
        long out = 0L;
        if ( (input & -2147483648) == -2147483648){
            out |= 2147483648L;
        }
        out |= (input & 2147483647);

        return Long.toString(out);
    }

    /**
     * Convert the integer int {@code input} as unsigned bits to an
     * integer long value.
     *
     * @param  input
     *         An int integer.
     *
     * @return  An integer long value of the {@code input}'s value
     *          as unsigned bits.
     *
     * @since  1.0.0.f
     **/
    public static long toLongAsUnsigned(final int input){
        long out = 0L;
        if ( (input & -2147483648) == -2147483648){
            out |= 2147483648L;
        }
        return out | (input & 2147483647);
    }
}
