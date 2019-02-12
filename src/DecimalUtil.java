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
 * <p>The <code>DecimalUtil</code> class is a final class that provides
 * static methods for converting decimal values in string representation
 * as bits to a Java's Primitive Data Type.
 *
 * <p>Currently this class supports converting decimal values in string
 * representation to integer int values and integer long values.
 *
 * <p>This class provides tools for comparing strings pertain to the
 * mathematical aspect. This class provide the method
 * {@link compareAsInt(String, String) compareAsInt} that can compare
 * strings by their actual digits values when the strings
 * contain valid decimal values that can be parsed to integer int values.
 *
 * <p>Strings can also be compared using the
 * {@link intOrSmaller(String, String) intOrSmaller} method, of which
 * compares the strings bases on their content reference to integer int
 * values.
 *
 * <p>Other method compareAs**** or ****OrSmaller(**** equals name) if
 * available, are similar to the
 * {@link compareAsInt(String, String) compareAsInt} or
 * {@link intOrSmaller(String, String) intOrSmaller} method, except they
 * are for the "name(****)" type.
 *
 * <p>This class only treats and parses decimal strings as signed values.
 * To parses decimal values as signed or unsigned values, see
 * {@link Base10Util Base10Util}.</p>
 *
 * @author  Khang Hoang Nguyen
 *
 * @since  1.0.0.f
 **/
public final class DecimalUtil{
    private DecimalUtil(){};
    /**
     * Parse the input string as signed decimal number into an integer
     * int value.
     *
     * @param  input
     *         A string to be parsed as signed decimal number to an
     *         integer int value.
     *
     * @return  An integer int value of the signed decimal string.
     *          {@code input} string.
     *
     * @throws  NumberFormatException
     *          If the {@code input} string contains invalid signed
     *          decimal digits, if the {@code input} string contains a
     *          value that is smaller than the value of
     *          Integer.MIN_VALUE({@value java.lang.Integer#MIN_VALUE}),
     *          or if the {@code input} string contains a value that
     *          is larger than the value of Integer.MAX_VALUE(
     *          {@value java.lang.Integer#MAX_VALUE}).
     *
     * @throws  EmptyStringException
     *          If the {@code input} string is empty.
     *
     * @since  1.0.0.f
     **/
    public static final int toInt(final String input) {
        final int length = input.length();
        if ( length == 0 ) throw new EmptyStringException(input);
        final char c1 = input.charAt(0); int start;

        if ( c1 == '-' || c1 == '+' ){
            if ( length == 1 ) throw new NumberFormatException(input);
            start = 1;
        } else {
            start = 0;
        }

        int out = 0, c;
        while( start < length && input.charAt(start) == '0' ) start++;
        final int runlen = length - start;

        if ( runlen > 9 ) {
            if ( runlen > 10 ) throw new NumberFormatException(input);
            out = (input.charAt(start++) ^ '0');
            if ( out > 2 ) throw new NumberFormatException(input);
        }

        for ( ; start < length; start++){
            c = (input.charAt(start) ^ '0');
            if ( c > 9 ) throw new NumberFormatException(input);
            out = (out << 1) + (out << 3) + c;
        }

        if ( c1 == '-' ){
            out = ~out + 1;
            if ( out > 0 ) throw new NumberFormatException(input);
            return out;
        }

        if ( out < 0 ) throw new NumberFormatException(input);
        return out;
    }

    /**
     * Parse the input string as signed decimal number into an integer
     * int value. This method throws true errors on unsuccessful parse
     * cases. This method may take longer on unsucessful parse cases.
     *
     * @param  input
     *         A string to be parsed as signed decimal number to an
     *         integer int value.
     *
     * @return  An integer int value of the signed decimal string
     *          {@code input} string.
     *
     * @throws  NumberFormatException
     *          If the {@code input} string contains invalid signed
     *          decimal digits.
     *
     * @throws  NumberUnderFlowException
     *          If the {@code input} string contains a value that is
     *          smaller than the value of Integer.MIN_VALUE(
     *          {@value Integer#MIN_VALUE}).
     *
     * @throws  NumberOverFlowException
     *          If the {@code input} string contains a value that is
     *          larger than the value of Integer.MAX_VALUE(
     *          {@value Integer#MAX_VALUE}).
     *
     * @throws  EmptyStringException
     *          If the {@code input} string is empty.
     *
     * @since  1.0.0.f
     **/
    public static final int toIntTrueError(final String input) {
        final int length = input.length();
        if ( length == 0 ) throw new EmptyStringException(input);
        final char c1 = input.charAt(0); int start;

        if ( c1 == '-' || c1 == '+' ){
            if ( length == 1 ) throw new NumberFormatException(input);
            start = 1;
        } else {
            start = 0;
        }

        int out = 0, c;
        while ( start < length && input.charAt(start) == '0' ) start++;
        final int runlen = length - start;

        if ( runlen > 9 ) {
            if ( runlen > 10 ){
                for ( ; start < length; start++){
                    if ( (input.charAt(start) ^ '0') > '\u0009' ) throw new NumberFormatException(input);
                }
                throwUnderOverError(c1, input);
            }

            out = (input.charAt(start++) ^ '0');
            if ( out > 9 ) throw new NumberFormatException(input);
            if ( out > 2 ){
                throwUnderOverError(c1, input);
            }
        }

        for ( ; start < length; start++){
            c = (input.charAt(start) ^ '0');
            if ( c > 9 ) throw new NumberFormatException(input);
            out = (out << 1) + (out << 3) + c;
        }

        if ( c1 == '-' ){
            out = ~out + 1;
            if ( out > 0 ) throw new NumberUnderFlowException(input);
            return out;
        }

        if ( out < 0 ) throw new NumberOverFlowException(input);
        return out;
    }

    /**
     * Parse the input string as signed decimal number into an integer
     * long value.
     *
     * @param  input
     *         A string to be parsed as signed decimal number to an
     *         integer long value.
     *
     * @return  An integer long value of the signed decimal {@code input}
     *          string.
     *
     * @throws  NumberFormatException
     *          If the {@code input} string contains invalid signed
     *          decimal digits, if the {@code input} string contains a
     *          value that is smaller than the value of Long.MIN_VALUE(
     *          {@value java.lang.Long#MIN_VALUE}), or if
     *          the {@code input} string contains a value that is larger
     *          than the value of Long.MAX_VALUE(
     *          {@value java.lang.Long#MAX_VALUE}).
     *
     * @throws  EmptyStringException
     *          If the {@code input} string is empty.
     *
     * @since  1.0.0.f
     **/
    public static final long toLong(final String input) {
        final int length = input.length();
        if ( length == 0 ) throw new EmptyStringException();
        final char c1 = input.charAt(0); int start;

        if ( c1 == '-' || c1 == '+' ){
            if ( length == 1 ) throw new NumberFormatException(input);
            start = 1;
        } else {
            start = 0;
        }

        long out = 0L, c;
        while ( start < length && input.charAt(start) == '0' ) start++;
        if ( length - start > 19 ) throw new NumberFormatException(input);

        for ( ; start < length; start++){
            c = (input.charAt(start) ^ '0');
            if ( c > 9L ) throw new NumberFormatException(input);
            out = (out << 1) + (out << 3) + c;
        }

        if ( c1 == '-' ){
            out = ~out + 1L;
            if ( out > 0L ) throw new NumberFormatException(input);
            return out;
        }
        if ( out < 0L ) throw new NumberFormatException(input);
        return out;
    }

    /**
     * Parse the input string as signed decimal number into an integer
     * long value. This method throws true errors on unsuccessful parse
     * cases. This method may take longer on unsucessful parse cases.
     *
     * @param  input
     *         A string to be parsed as signed decimal number to an
     *         integer long value.
     *
     * @return  An integer long value of the signed decimal {@code input}
     *          string.
     *
     * @throws  NumberFormatException
     *          If the {@code input} string contains invalid decimal
     *          digits.
     *
     * @throws  NumberUnderFlowException
     *          If the {@code input} string contains a value that is
     *          smaller than the value of Long.MIN_VALUE(
     *          {@value Long#MIN_VALUE}).
     *
     * @throws  NumberOverFlowException
     *          If the {@code input} string contains a value that is
     *          larger than the value of Long.MAX_VALUE(
     *          {@value Long#MAX_VALUE}).
     *
     * @throws  EmptyStringException
     *          If the {@code input} string is empty.
     *
     * @since  1.0.0.f
     **/
    public static final long toLongTrueError(final String input) {
        final int length = input.length();
        if ( length == 0 ) throw new EmptyStringException(input);
        final char c1 = input.charAt(0); int start;

        if ( c1 == '-' || c1 == '+' ){
            if ( length == 1 ) throw new NumberFormatException(input);
            start = 1;
        } else {
            start = 0;
        }

        long out = 0L, c;
        while ( start < length && input.charAt(start) == '0' ) start++;
        if ( length - start > 19 ){
            for ( ; start < length; start++){
                if ( (input.charAt(start) ^ '0') > '\u0009' ) throw new NumberFormatException(input);
            }
            throwUnderOverError(c1, input);
        }

        for ( ; start < length; start++){
            c = (input.charAt(start) ^ '0');
            if ( c > 9L ) throw new NumberFormatException(input);
            out = (out << 1) + (out << 3) + c;
        }

        if ( c1 == '-' ){
            out = ~out + 1L;
            if ( out > 0L ) throw new NumberUnderFlowException(input);
            return out;
        }

        if ( out < 0L ) throw new NumberOverFlowException(input);
        return out;
    }

    /**
     * Compare two decimal number strings as integer int values. This
     * method treat integer int values as signed integer type.
     * This method does not throw errors. However, the
     * {@link #toInt(String) toInt} method uses by this method throws
     * errors.
     *
     * @param  firstNumber
     *         A string to be compared to the string {@code secondNumber}.
     *
     * @param  secondNumber
     *         A string to be compared to the string {@code firstNumber}.
     *
     * @return  An integer int value of 1 if the {@code firstNumber}
     *          string is larger than the {@code secondNumber} string,
     *          0 if they are both equal, or -1 if the
     *          {@code firstNumber} string is smaller than the
     *          {@code secondNumber} string.
     *
     * @see  #toInt(String)
     *
     * @since  1.0.0.f
     **/
    public static int compareAsInt(final String firstNumber, final String secondNumber){
        final int n1 = toInt(firstNumber),
                  n2 = toInt(secondNumber);

        if ( n1 > n2 ) return 1;
        else if ( n1 < n2 ) return -1;
        return 0;
    }

     /**
     * <p>Compare two strings bases on the content of the strings
     * reference to an integer int value. If the strings are valid decimal
     * number strings that can be parse as integer int values then
     * they will be compared bases on their integer int values.
     * Otherwise, the strings will be compared bases on the priority
     * ranking order below. This method treat integer int values as
     * signed integer type.
     *
     * <p>Priority order ranking: (lo - hi)</br>
     * 0 - Invalid number strings</br>
     * 1 - Underflow</br>
     * 2 - Overflow</br>
     * 3 - Empty strings</br>
     * 4 - Valid int values</p>
     *
     * @param  firstNumber
     *         A string to be compared to the string {@code secondNumber}.
     *
     * @param  secondNumber
     *         A string to be compared to the string {@code firstNumber}.
     *
     * @return  An integer int value of 1 if the {@code firstNumber}
     *          string is larger than the {@code secondNumber} string,
     *          0 if they are both equal, or -1 if the
     *          {@code firstNumber} string is smaller than the
     *          {@code secondNumber} string.
     *
     * @since  1.0.0.f
     **/
    public static int intOrSmaller(final String firstNumber, final String secondNumber){
        if ( firstNumber.length() == 0 ){
            if ( secondNumber.length() == 0 ) return 0;
            try{
                toInt(secondNumber);
                return -1;
            } catch (NumberFormatException e){
                return 1;
            }
        } else if ( secondNumber.length() == 0 ){
            try{
                toInt(firstNumber);
                return 1;
            } catch (NumberFormatException e){
                return -1;
            }
        }

        int n1p, n2p;
        int n1 = 0, n2 = 0;

        try{
            n1 = toIntTrueError(firstNumber);
            n1p = 4;
        } catch(NumberFormatException e) {
            n1p = 0;
        } catch(NumberUnderFlowException e) {
            n1p = 1;
        } catch(NumberOverFlowException e){
            n1p = 2;
        }

        try{
            n2 = toIntTrueError(secondNumber);
            n2p = 4;
        } catch(NumberFormatException e) {
            n2p = 0;
        } catch(NumberUnderFlowException e) {
            n2p = 1;
        } catch(NumberOverFlowException e){
            n2p = 2;
        }

        if ( n1p == 4 && n2p == 4 ){
            if ( n1 > n2 ) return 1;
            else if ( n1 < n2 ) return -1;
            return 0;
        }

        if ( n1p > n2p ) return 1;
        else if ( n1p < n2p ) return -1;
        return 0;
    }

    /**
     * Compare two decimal number strings as integer long values. This
     * method treat integer long values as signed integer type.
     * This method does not throw errors. However, the
     * {@link #toLong(String) toLong} method uses by this method throws
     * errors.
     *
     * @param  firstNumber
     *         A string to be compared to the string {@code secondNumber}.
     *
     * @param  secondNumber
     *         A string to be compared to the string {@code firstNumber}.
     *
     * @return  An integer int value of 1 if the {@code firstNumber}
     *          string is larger than the {@code secondNumber} string,
     *          0 if they are both equal, or -1 if the
     *          {@code firstNumber} string is smaller than the
     *          {@code secondNumber} string.
     *
     * @see  #toLong(String)
     *
     * @since  1.0.0.f
     **/
    public static int compareAsLong(final String firstNumber, final String secondNumber){
        final long n1 = toLong(firstNumber),
                   n2 = toLong(secondNumber);

        if ( n1 > n2 ) return 1;
        else if ( n1 < n2 ) return -1;
        return 0;
    }

    /**
     * <p>Compare two strings bases on the content of the strings
     * reference to an integer long value. If the strings are valid
     * decimal number strings that can be parse as integer long values
     * then they will be compared bases on their integer long values.
     * Otherwise, the strings will be compared bases on the priority
     * ranking order below. This method treat integer long values as
     * signed integer type.
     *
     * <p>Priority order ranking: (lo - hi)</br>
     * 0 - Invalid number strings</br>
     * 1 - Underflow</br>
     * 2 - Overflow</br>
     * 3 - Empty strings</br>
     * 4 - Valid long values</p>
     *
     * @param  firstNumber
     *         A string to be compared to the string {@code secondNumber}.
     *
     * @param  secondNumber
     *         A string to be compared to the string {@code firstNumber}.
     *
     * @return  An integer int value of 1 if the {@code firstNumber}
     *          string is larger than the {@code secondNumber} string,
     *          0 if they are both equal, or -1 if the
     *          {@code firstNumber} string is smaller than the
     *          {@code secondNumber} string.
     *
     * @since  1.0.0.f
     **/
    public static int longOrSmaller(final String firstNumber, final String secondNumber){
        if ( firstNumber.length() == 0 ){
            if ( secondNumber.length() == 0 ) return 0;
            try{
                toLong(secondNumber);
                return -1;
            } catch (NumberFormatException e){
                return 1;
            }
        } else if ( secondNumber.length() == 0 ){
            try{
                toLong(firstNumber);
                return 1;
            } catch (NumberFormatException e){
                return -1;
            }
        }

        int n1p, n2p;
        long n1 = 0L, n2 = 0L;

        try{
            n1 = toLongTrueError(firstNumber);
            n1p = 4;
        } catch(NumberFormatException e) {
            n1p = 0;
        } catch(NumberUnderFlowException e) {
            n1p = 1;
        } catch(NumberOverFlowException e){
            n1p = 2;
        }

        try{
            n2 = toLongTrueError(secondNumber);
            n2p = 4;
        } catch(NumberFormatException e) {
            n2p = 0;
        } catch(NumberUnderFlowException e) {
            n2p = 1;
        } catch(NumberOverFlowException e){
            n2p = 2;
        }

        if ( n1p == 4 && n2p == 4 ){
            if ( n1 > n2 ) return 1;
            else if ( n1 < n2 ) return -1;
            return 0;
        }

        if ( n1p > n2p ) return 1;
        else if ( n1p < n2p ) return -1;
        return 0;
    }

    /*
     * Private method to optimize compile code size per method where needed.
     **/
    private static final void throwUnderOverError (final char c, final String str) {
        if ( c == '-' ) throw new NumberUnderFlowException(str);
        throw new NumberOverFlowException(str);
    }
}
