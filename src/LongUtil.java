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
 * <p>The <code>LongUtil</code> class is a final class that provides
 * methods for working with integer long values.
 *
 * <p>This class provides methods for parsing integer long values to
 * signed hexadecimal or signed octal digits in string representation.
 *
 * <p>This class supports parsing long values as unsigned long to
 * string of decimal digits.</p>
 *
 * @author  Khang Hoang Nguyen
 *
 * @since  1.0.0.f
 **/
public final class LongUtil{
    private LongUtil(){};

    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                          'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * Check if the input is an odd number.
     *
     * @param  input
     *         A long integer.
     *
     * @return  A boolean value of true if the {@code input} is an odd
     *          number or false if it is not.
     *
     * @since  1.0.0.f
     **/
    public static boolean isOdd(final long input){
        return ( (input & 1L) == 1L );
    }

    /**
     * Check if the input is an even number.
     *
     * @param  input
     *         A long integer.
     *
     * @return  A boolean value of true if the input is an even number
     *          or false if it is not.
     *
     * @since  1.0.0.f
     **/
    public static boolean isEven(final long input){
        return ( (input & 1L) == 0L );
    }

    /**
     * Parse the integer long {@code input} to a string of signed binary
     * digits.
     *
     * @param  input
     *         A long integer.
     *
     * @return  A string of signed binary digits of the {@code input}'s
     *          value.
     *
     * @since  1.0.0.f
     **/
    public static String toSignedBinary(long input){
        final char[] out = new char[64]; int pos = 64;
        if ( input < 0L ){
            if ( input == -9223372036854775808L ) return "-1000000000000000000000000000000000000000000000000000000000000000";
            input = ~input + 1L;
            while ( input != 0L ){
                out[--pos] = (char)((input & 1L) ^ 48L);
                input = input >> 1;
            }
            out[--pos] = '-';
        } else if ( input > 0L ) {
            while ( input != 0L ){
                out[--pos] = (char)((input & 1L) ^ 48L);
                input = input >> 1;
            }
        } else {
            return "0";
        }

        return new String(out, pos, 64 - pos);
    }

    /**
     * Parse the integer long {@code input} to a string of signed octal
     * digits.
     *
     * @param  input
     *         A long integer.
     *
     * @return  A string of signed octal digits of the {@code input}'s
     *          value.
     *
     * @since  1.0.0.f
     **/
    public static String toSignedOctal(long input){
        final char[] out = new char[23]; int pos = 23;
        if ( input < 0L ){
            if ( input == -9223372036854775808L ) return "-1000000000000000000000";
            input = ~input + 1L;
            while ( input != 0L ){
                out[--pos] = (char)((input & 7L) ^ 48L);
                input = input >> 3;
            }
            out[--pos] = '-';
        } else if ( input > 0L ) {
             while ( input != 0L ){
                out[--pos] = (char)((input & 7L) ^ 48L);
                input = input >> 3;
            }
        } else {
            return "0";
        }

        return new String(out, pos, 23 - pos);
    }

    /**
     * Parse the integer long {@code input} to a string of signed
     * hexadecimal digits.
     *
     * @param  input
     *         A long integer.
     *
     * @return  A string of signed hexadecimal digits of the
     *          {@code input}'s value.
     *
     * @since  1.0.0.f
     **/
    public static String toSignedHex(long input){
        final char[] out = new char[17]; int pos = 17;
        if ( input < 0L ){
            if ( input == -9223372036854775808L ) return "-8000000000000000";
            input = ~input + 1L;
            while ( input != 0L ){
                out[--pos] = digits[(int)(input & 15L)];
                input = input >> 4;
            }
            out[--pos] = '-';
        } else if ( input > 0L ) {
            while ( input != 0L ){
                out[--pos] = digits[(int)(input & 15L)];
                input = input >> 4;
            }
        } else {
            return "0";
        }

        return new String(out, pos, 17 - pos);
    }

    /**
     * Parse the integer long {@code input} to a string of decimal as
     * unsigned bits.
     *
     * Take note that this method is a bit slow currently.
     *
     * @param  input
     *         A long integer.
     *
     * @return  A string of decimal of the {@code input}'s value as
     *          unsigned bits.
     *
     * @since  1.0.0.f
     **/
    /*
     * Formula is a modified version of one of the formula from
     * https://en.wikipedia.org/wiki/Division_algorithm.
     **/
    public static String toStringAsUnsigned(long input){
        if ( input == 0L ) return "0";
        final char[] out = new char[20]; int pos = 20;
        long shift, quotient;
        int remainder;

        while ( input != 0 ){
            quotient = 0L;
            remainder = 0;
            for ( int i = 63; i > -1; i-- ){
                remainder <<= 1;
                shift = (1L << i);

                if ( (input & shift ) != 0L ) remainder |= 1;

                if (remainder > 9) {
                    remainder = remainder - 10;
                    quotient |= shift;
                }
            }

            out[--pos] = digits[remainder];
            input = quotient;
        }

        return new String(out, pos, 20 - pos);
    }
}
