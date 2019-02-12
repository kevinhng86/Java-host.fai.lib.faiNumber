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
 * <p>The <code>NumberStringUtil</code> class is a final class that
 * provides methods for working with numbers in String format.
 *
 * <p>This class provide methods for validating strings to see whether
 * they are valid number, validating chars to see whether if they are 
 * valid digits, and comparing strings of number.
 * 
 * <p>This class has the assume**** methods, of which operate on strings
 * with the assumption that the strings are valid number values. For
 * comparing string of numbers, the assume**** methods are very fast
 * methods that can be used in circumstances where it is sure that the
 * strings are valid number values.</p>
 *
 * <p>Under the right circumstance, the assume**** methods are very
 * powerful and fast methods. However, under that use case, the strings
 * must be valid number strings for the methods to produce the correct
 * result. Thus, the assume**** methods of this class are good for 
 * circumstances where the input data have been validated before usage
 * and there isn't a need for revalidating the data. 
 *
 * <p>For an example of one of the assume**** methods of this class, 
 * The assumeCompare methods of this class besides being able to compare
 * numbers it can also compare dates with fix format. Format like
 * YYYY-MM-DD, YYYY/MM/DD, or similar can be compared. However, for
 * correct results, the month, day, or year value must consistenly be in
 * the same amount of digits(i.e. year 500 and 2000 have to be written
 * as 0500 and 1000, or month 1 and 12 have to be written as 01 and 12).
 * Also, year always have to be first, then month, then finally day.
 *
 * <p>Example usage of the {@link #assumeCompare(String, String) assumeCompare}
 * method.
 *
 * <p><pre>
 *     String a = "1208925819614629174706176";
 *     String b = "1208925819614629174706177";
 *     System.out.println(NumberStringUtil.assumeCompare(a,b));
 *
 *     String d1 = "2018-01-23";
 *     String d2 = "2018-12-24";
 *     System.out.println(NumberStringUtil.assumeCompare(d1,d2));
 *
 *     String d3 = "2018-12-24";
 *     String d4 = "2018-12-03";
 *     System.out.println(NumberStringUtil.assumeCompare(d3,d4));
 * </pre></p>
 * 
 * <p>For comparing strings of numbers, besides the 
 * {@link #assumeCompare(String, String) assumeCompare} or the 
 * {@link #assumeCompareAllBase(String, String) assumeCompareAllBase}
 * method, this class also provide the 
 * {@link #compare(String, String) compare} and the 
 * {@link #compareAsBase(String, String, int) compareAsBase} method
 * for comparing strings of number where there is a need to validate
 * the strings when comparing. 
 * 
 * <p>For speed wise, the assumeCompare**** and compare**** methods 
 * of this class excel in circumstance where the strings do not 
 * have to be compared up until the last digit to know which string is
 * larger or smaller.
 * 
 * <p>Another advantage of the compare**** and assumeCompare**** methods
 * is that, there isn't a upper bound on the length of the input strings.
 * That is as long as memory allow.</p>
 * 
 * @author  Khang Hoang Nguyen
 *
 * @since  1.0.0.f
 **/
public final class NumberStringUtil{
    private NumberStringUtil(){};
    /**
     * Check if a string is a valid signed decimal integer.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid signed decimal integer, or otherwise, false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isInteger (final String input){
        int length = input.length();
        if ( length == 0 ) return false;
        final int first;

        if ( input.charAt(0) == '-' || input.charAt(0) == '+' ) {
            if ( length == 1 ) return false;
            first = 1;
        } else first = 0;

        while( length-- > first ){
            if ( (input.charAt(length) ^ '0') > 9 ) return false;
        }

        return true;
    }

    /**
     * Check if a string is a valid unsigned decimal integer.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid unsigned decimal integer, or otherwise,
     *          false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isUnsignedInteger (final String input){
        int length = input.length();
        if ( length == 0 ) return false;

        while( length-- > 0 ){
            if ( (input.charAt(length) ^ '0') > 9 ) return false;
        }

        return true;
    }

    /**
     * Check if a string is a valid signed binary integer.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid signed binary integer, or otherwise, false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isBinary (final String input){
        int length = input.length();
        if ( length == 0 ) return false;
        final int first;

        if ( input.charAt(0) == '-' || input.charAt(0) == '+' ) {
            if ( length == 1 ) return false;
            first = 1;
        } else first = 0;

        while( length-- > first ){
            if ( (input.charAt(length) ^ '0') > 1 ) return false;
        }

        return true;
    }

    /**
     * Check if a string is a valid unsigned binary integer.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid unsigned binary integer, or otherwise,
     *          false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isUnsignedBinary (final String input){
        int length = input.length();
        if ( length == 0 ) return false;

        while( length-- > 0 ){
            if ( (input.charAt(length) ^ '0') > 1 ) return false;
        }

        return true;
    }

    /**
     * Check if a string is a valid signed octal integer.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid signed octal integer, or otherwise, false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isOctal (final String input){
        int length = input.length();
        if ( length == 0 ) return false;
        final int first;

        if ( input.charAt(0) == '-' || input.charAt(0) == '+' ) {
            if ( length == 1 ) return false;
            first = 1;
        } else first = 0;

        while( length-- > first ){
            if ( (input.charAt(length) ^ '0') > 7 ) return false;
        }

        return true;
    }

    /**
     * Check if a string is a valid unsigned octal integer.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid unsigned octal integer, or otherwise, false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isUnsignedOctal (final String input){
        int length = input.length();
        if ( length == 0 ) return false;

        while( length-- > 0 ){
            if ( (input.charAt(length) ^ '0') > 7 ) return false;
        }

        return true;
    }

    /**
     * Check if a string is a valid signed hexadecimal integer.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid signed hexadecimal integer, or otherwise,
     *          false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isHex(final String input){
        int length = input.length();
        if ( length == 0 ) return false;
        final int first;

        if ( input.charAt(0) == '-' || input.charAt(0) == '+' ) {
            if ( length == 1 ) return false;
            first = 1;
        } else first = 0;

        int c;

        while (length-- > first){
            c = input.charAt(length);

            if ( c > 96 & c < 103 ){}
            else if ( c > 64 && c < 71 ){}
            else {
                c ^= 48;
                if ( c > 9 ) return false;
            }
        }

        return true;
    }

    /**
     * Check if a string is a valid unsigned hexadecimal integer.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid unsigned hexadecimal integer, or otherwise,
     *          false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isUnsignedHex(final String input){
        int length = input.length();
        if ( length == 0 ) return false;

        int c;

        while (length-- > 0){
            c = input.charAt(length);

            if ( c > 96 && c < 103 ){}
            else if ( c > 64 && c < 71 ){}
            else {
                c ^= 48;
                if ( c > 9 ) return false;
            }
        }

        return true;
    }

    /**
     * Check if a string is a valid signed integer of the defined radix.
     *
     * @param  input
     *         A string.
     *
     * @param  base
     *         An integer int value that defines the number radix.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid signed integer of the radix that defined by
     *          the {@code base} int, or otherwise, false.
     *
     * @throws  UnsupportedOperationException
     *          If the input {@code base} int value is a value that is
     *          smaller than 2 or larger than 36.
     *
     * @since  1.0.0.f
     **/
    public static boolean isBase(final String input, final int base){
        if ( base < 2 || base > 36 ) throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        int length = input.length();
        if ( length == 0 ) return false;
        final int first;

        if ( input.charAt(0) == '-' || input.charAt(0) == '+' ) {
            if ( length == 1 ) return false;
            first = 1;
        } else first = 0;

        int c;

        while (length-- > first){
            c = input.charAt(length);

            if ( c > 96 ) c -= 87;
            else if ( c > 64 ) c -= 55;
            else {
                c ^= 48;
                if ( c > 9 ) return false;
            }

            if ( !(c < base) ) return false;
        }

        return true;
    }

    /**
     * Check if a string is a valid unsigned integer of the defined radix.
     *
     * @param  input
     *         A string.
     *
     * @param  base
     *         An integer int value that defines the number radix.
     *
     * @return  A boolean value of true if the {@code input} string
     *          is a valid unsigned integer of the radix that defined by
     *          the {@code base} int, or otherwise, false.
     *
     * @throws  UnsupportedOperationException
     *          If the input {@code base} int value is a value that is
     *          smaller than 2 or larger than 36.
     *
     * @since  1.0.0.f
     **/
    public static boolean isUnsignedBase(final String input, final int base){
        if ( base < 2 || base > 36 ) throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        int length = input.length();
        if ( length == 0 ) return false;

        int c;

        while ( length-- > 0 ){
            c = input.charAt(length);

            if ( c > 96 ) c -= 87;
            else if ( c > 64 ) c -= 55;
            else {
                c ^= 48;
                if ( c > 9 ) return false;
            }

            if ( !(c < base) ) return false;
        }

        return true;
    }

    /**
     * Check if a char is a valid decimal digit.
     *
     * @param  input
     *         A char.
     *
     * @return  A boolean value of true if the {@code input} char is
     *          a valid decimal digit, or otherwise, false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isDigit(final char input){
        return (input ^ '0') < 10;
    }

    /**
     * Check if a char is a valid binary digit.
     *
     * @param  input
     *         A char.
     *
     * @return  A boolean value of true if the {@code input} char is
     *          a valid binary digit, or otherwise, false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isBinaryDigit(final char input){
        return ( input == '0' || input == '1');
    }

    /**
     * Check if a char is a valid octal digit.
     *
     * @param  input
     *         A char.
     *
     * @return  A boolean value of true if the {@code input} char is
     *          a valid octal digit, or otherwise, false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isOctalDigit(final char input){
        return (input ^ '0') < 8;
    }

    /**
     * Check if a char is a valid hexadecimal digit.
     *
     * @param  input
     *         A char.
     *
     * @return  A boolean value of true if the {@code input} char is
     *          a valid hexadecimal digit, or otherwise, false.
     *
     * @since  1.0.0.f
     **/
    public static boolean isHexDigit(final char input){
        return ( (input > 47 && input < 58) || (input > 64 && input < 71) || (input > 96 && input < 103) );
    }

    /**
     * Check if a char is a valid digit of a defined radix.
     *
     * @param  input
     *         A char.
     *
     * @param  base
     *         An integer int value that defines the number radix.
     *
     * @return  A boolean value of true if the {@code input} char is
     *          a valid digit of a radix that defined by the {@code base}
     *          int, or otherwise, false.
     *
     * @throws  UnsupportedOperationException
     *          If the input {@code base} int value is a value that is
     *          smaller than 2 or larger than 36.
     *
     * @since  1.0.0.f
     **/
    public static boolean isBaseDigit(char input, final int base){
        if ( base < 2 || base > 36 ) throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");

        if ( input > 96) input -= 87;
        else if ( input > 64) input -= 55;
        else {
            input ^= 48;
            if ( input > 9 ) return false;
        }

        return input < base;
    }

    /**
     * Check if a string a odd number with the assumption that the
     * string is a valid decimal integer in string representation.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the last character in the
     *          string is an odd value, or false for everything else.
     *
     * @since  1.0.0.f
     **/
    public static boolean assumeIsOdd (final String input){
        final int length = input.length();
        if ( length == 0 ) throw new EmptyStringException();
        return ((input.charAt(length - 1) & 1) != 0);
    }

    /**
     * Check if a string an even number with the assumption that the
     * string is a valid decimal integer in string representation.
     *
     * @param  input
     *         A string.
     *
     * @return  A boolean value of true if the last character in the
     *          string is an even value, or false for everything else.
     *
     * @since  1.0.0.f
     **/
    public static boolean assumeIsEven (final String input){
        final int length = input.length();
        if ( length == 0 ) throw new EmptyStringException();
        return ((input.charAt(length - 1) & 1) == 0);
    }

    /**
     * <p>Compare two strings with the assumption that both strings are
     * valid decimal integers.
     *
     * <p>This method evaluates both, negative and positive values. This
     * method may produce unwanted result if the previous is not met or
     * if the number formatting is incorrect.
     *
     * <p>This method considers empty strings as smallest. This method
     * disregards leading zeroes. This method considers a string with
     * only a '-' or a '+' sign as 0.
     * </p>
     *
     * @param  firstString
     *         A string to be compared to the {@code secondString} string.
     *
     * @param  secondString
     *         A string to be compared to the {@code firstString} string.
     *
     * @return  An integer int of 1 if the {@code firstString} is larger
     *          than the {@code secondString}, -1 if the
     *          {@code firstString} is smaller than the {@code secondString},
     *          or 0 if both strings are equal.
     *
     * @since  1.0.0.f
     **/
    public static int assumeCompare (final String firstString, final String secondString){
        final int str1length = firstString.length(),
                  str2length = secondString.length();

        if ( str1length == 0 ){
            if ( str2length == 0 ) return 0;
            return -1;
        } else if (str2length == 0){
            return 1;
        }

        boolean neg1 = false, neg2 = false;
        int start1 = 0, start2 = 0;

        if( firstString.charAt(0) == '-' ){
            neg1 = true;
            start1 = 1;
        } else if( firstString.charAt(0) == '+' ) start1 = 1;

        if( secondString.charAt(0) == '-' ){
            neg2 = true;
            start2 = 1;
        } else if( secondString.charAt(0) == '+' ) start2 = 1;

        while( start1 < str1length && firstString.charAt(start1) == '0' ) start1++;
        while( start2 < str2length && secondString.charAt(start2) == '0' ) start2++;
              int runlen1 = str1length - start1;
        final int runlen2 = str2length - start2;

        if ( runlen1 == 0 ){
            if ( runlen2 == 0 ) return 0;
            if ( neg2 == true ) return 1;
            return -1;
        } else if ( runlen2 == 0 ){
            if ( neg1 == true ) return -1;
            return 1;
        }

        if ( neg1 != neg2 ){
            if ( neg1 == false ) return 1;
            if ( neg1 == true ) return -1;
        }

        char c1, c2;

        if ( neg1 == true ){
            if ( runlen1 > runlen2 ) return -1;
            else if ( runlen1 < runlen2 ) return 1;

            while ( runlen1-- != 0 ){
                c1 = firstString.charAt(start1++);
                c2 = secondString.charAt(start2++);
                if ( c1 < c2 ) return 1;
                if ( c1 > c2 ) return -1;
            }
        } else if ( neg1 == false ){
            if ( runlen1 > runlen2 ) return 1;
            else if ( runlen1 < runlen2 ) return -1;

            while( runlen1-- != 0 ){
                c1 = firstString.charAt(start1++);
                c2 = secondString.charAt(start2++);
                if ( c1 > c2 ) return 1;
                if ( c1 < c2 ) return -1;
            }
        }

        return 0;
    }

    /**
     * <p>Compare two strings with the assumption that both strings are
     * valid integers of any radix between 2 and 36.
     *
     * <p>This method evaluates both, negative and positive values.
     * This method will work, if both strings are integers of the same
     * radix and the radix is from 2 to 36. This method may produce
     * unwanted result if the previous are not met or if the formatting
     * is incorrect.
     *
     * <p>Digits of radix larger than 10 are 'a' to 'z' with
     * 'a'  being 10, and 'z' being 35. A is equal to a, Z is equal to
     * z, and so forth.
     *
     * <p>This method considers empty strings as smallest. This method
     * disregards leading zeroes. This method considers a string with
     * only a '-' or a '+' sign as 0.
     * </p>
     *
     * @param  firstString
     *         A string to be compared to the {@code secondString} string.
     *
     * @param  secondString
     *         A string to be compared to the {@code firstString} string.
     *
     * @return  An integer int of 1 if the {@code firstString} is larger
     *          than the {@code secondString}, -1 if the
     *          {@code firstString} is smaller than the {@code secondString},
     *          or 0 if both strings are equal.
     *
     * @since  1.0.0.f
     **/
    public static int assumeCompareAllBase (final String firstString, final String secondString){
        final int str1length = firstString.length(),
                  str2length = secondString.length();

        if ( str1length == 0 ){
            if ( str2length == 0 ) return 0;
            return -1;
        } else if ( str2length == 0){
            return 1;
        }

        boolean neg1 = false, neg2 = false;
        int start1 = 0, start2 = 0;

        if( firstString.charAt(0) == '-' ){
            neg1 = true;
            start1 = 1;
        } else if( firstString.charAt(0) == '+' ) start1 = 1;

        if( secondString.charAt(0) == '-' ){
            neg2 = true;
            start2 = 1;
        } else if( secondString.charAt(0) == '+' ) start2 = 1;

        while( start1 < str1length && firstString.charAt(start1) == '0' ) start1++;
        while( start2 < str2length && secondString.charAt(start2) == '0' ) start2++;
              int runlen1 = str1length - start1;
        final int runlen2 = str2length - start2;

        if ( runlen1 == 0 ){
            if ( runlen2 == 0 ) return 0;
            if ( neg2 == true ) return 1;
            return -1;
        } else if ( runlen2 == 0 ){
            if ( neg1 == true ) return -1;
            return 1;
        }

        if ( neg1 != neg2 ){
            if ( neg1 == false ) return 1;
            if ( neg1 == true ) return -1;
        }

        int c1, c2;

        if ( neg1 == true ){
            if ( runlen1 > runlen2 ) return -1;
            else if ( runlen1 < runlen2 ) return 1;

            while ( runlen1-- != 0 ){
                c1 = firstString.charAt(start1++);
                c2 = secondString.charAt(start2++);

                if ( c1 > 96) c1 -= 87;
                else if ( c1 > 64) c1 -= 55;
                else c1 ^= 48;

                if ( c2 > 96) c2 -= 87;
                else if ( c2 > 64) c2 -= 55;
                else c2 ^= 48;

                if ( c1 < c2 ) return 1;
                if ( c1 > c2 ) return -1;
            }
        } else if ( neg1 == false ){
            if ( runlen1 > runlen2 ) return 1;
            else if ( runlen1 < runlen2 ) return -1;

            while( runlen1-- != 0 ){
                c1 = firstString.charAt(start1++);
                c2 = secondString.charAt(start2++);

                if ( c1 > 96) c1 -= 87;
                else if ( c1 > 64) c1 -= 55;
                else c1 ^= 48;

                if ( c2 > 96) c2 -= 87;
                else if ( c2 > 64) c2 -= 55;
                else c2 ^= 48;

                if ( c1 > c2 ) return 1;
                if ( c1 < c2 ) return -1;
            }
        }

        return 0;
    }
    
    /**
     * <p>Compare two strings as decimal integers. This method evaluates
     * both, negative and positive values. This method throw errors 
     * if either one of the strings is an invalid decimal integer.
     * Nonetheless, the strings can be very large in length. This method
     * disregard leading zeroes.
     *
     * @param  firstString
     *         A string to be compared to the {@code secondString} string.
     *
     * @param  secondString
     *         A string to be compared to the {@code firstString} string.
     *
     * @return  An integer int of 1 if the {@code firstString} is larger
     *          than the {@code secondString}, -1 if the
     *          {@code firstString} is smaller than the {@code secondString},
     *          or 0 if both strings are equal.
     *
     * @throws  NumberFormatException
     *          If the either one of the strings is not a valid decimal
     *          integer. A single '+' or '-' sign without any digit is
     *          an invalid decimal integer value.
     * 
     * @throws  EmptyStringException
     *          If either one of the strings is empty. 
     *
     * @since  1.0.0.f
     **/
    public static int compare (final String firstString, final String secondString){
        final int str1length = firstString.length(),
                  str2length = secondString.length();
        int out = 2;

        if ( str1length == 0 || str2length == 0 ){
            throw new EmptyStringException();
        } 

        boolean neg1 = false, neg2 = false;
        int start1 = 0, start2 = 0;

        if( firstString.charAt(0) == '-' ){
            if ( str1length == 1 ) throw new NumberFormatException(firstString);
            neg1 = true;
            start1 = 1;
        } else if( firstString.charAt(0) == '+' ){
            if ( str1length == 1 ) throw new NumberFormatException(firstString);
            start1 = 1;
        }

        if( secondString.charAt(0) == '-' ){
            if ( str2length == 1 ) throw new NumberFormatException(secondString);
            neg2 = true;
            start2 = 1;
        } else if( secondString.charAt(0) == '+' ){
            if ( str2length == 1 ) throw new NumberFormatException(secondString);
            start2 = 1;
        }

        while( start1 < str1length && firstString.charAt(start1) == '0' ) start1++;
        while( start2 < str2length && secondString.charAt(start2) == '0' ) start2++;
        int runlen1 = str1length - start1,
            runlen2 = str2length - start2;

        if ( runlen1 == 0 ){
            if ( runlen2 == 0 ) out = 0;
            else if ( neg2 == true ) out = 1;
            else out = -1;
        } else if ( runlen2 == 0 ){
            if ( neg1 == true ) out = -1;
            else out = 1;
        }
 
        if ( out == 2 && neg1 != neg2 ){
            if ( neg1 == false ) out = 1;
            if ( neg1 == true ) out = -1;
        }
        
        if ( out == 2 ){
            char c1, c2;
            if ( neg1 == true ){
                if ( runlen1 > runlen2 ) out = -1;
                else if ( runlen1 < runlen2 ) out = 1;
 
                if ( out == 2 ){
                    while ( runlen1-- != 0 ){
                        c1 = firstString.charAt(start1++);
                        c2 = secondString.charAt(start2++);
                        if ( (c1 ^ '0') > 9 ) throw new NumberFormatException(firstString);
                        if ( (c2 ^ '0') > 9 ) throw new NumberFormatException(secondString);
                                                
                        if ( c1 < c2 ){
                            out = 1;
                            break;
                        } 
                        
                        if ( c1 > c2 ){
                            out = -1;
                            break;
                        }
                    }
                }
            } else if ( neg1 == false ){
                if ( runlen1 > runlen2 ) out = 1;
                else if ( runlen1 < runlen2 ) out = -1;
 
                if ( out == 2 ){
                    while( runlen1-- != 0 ){
                        c1 = firstString.charAt(start1++);
                        c2 = secondString.charAt(start2++);
                        if ( (c1 ^ '0') > 9 ) throw new NumberFormatException(firstString);
                        if ( (c2 ^ '0') > 9 ) throw new NumberFormatException(secondString);

                        if ( c1 > c2 ) {
                            out = 1;
                            break;
                        }
                        
                        if ( c1 < c2 ) {
                            out = -1;
                            break;
                        }
                    }
                }
            }
        }

        while ( start1 < str1length  ) {
            if ( (firstString.charAt(start1++) ^ '0') > 9 ) throw new NumberFormatException(firstString);
        }
        while ( start2 < str2length ) {
            if ( (secondString.charAt(start2++) ^ '0') > 9 ) throw new NumberFormatException(secondString);
        }

        if ( out == 2 ) return 0;
        return out;
    }
    
    /**
     * <p>Compare two strings as integers of a define radix. This method
     * evaluates both, negative and positive values. This method throw
     * errors if either one of the strings is an invalid integer of the
     * defined radix. The radix of the strings can only be in between of 
     * 2 to 36. There isn't a maximum length for the strings. Nonetheless,
     * the strings can't be empty.
     *
     * @param  firstString
     *         A string to be compared to the {@code secondString} string.
     *
     * @param  secondString
     *         A string to be compared to the {@code firstString} string.
     *
     * @param  base
     *         An integer int value that defines the number radix for
     *         both strings.
     * 
     * @return  An integer int of 1 if the {@code firstString} is larger
     *          than the {@code secondString}, -1 if the
     *          {@code firstString} is smaller than the {@code secondString},
     *          or 0 if both strings are equal.
     *
     * @throws  NumberFormatException
     *          If the either one of the strings is not a valid integer
     *          number. A single '+' or '-' sign without any digit is
     *          an invalid integer value.
     * 
     * @throws  EmptyStringException
     *          If either one of the strings is empty. 
     * 
     * @throws  UnsupportedOperationException
     *          If the input {@code base} int value is a value that is
     *          smaller than 2 or larger than 36.
     * 
     * @since  1.0.0.f
     **/
    public static int compareAsBase (final String firstString, final String secondString, final int base){
        if( base < 2 || base > 36 ) throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        final int str1length = firstString.length(),
                  str2length = secondString.length();
        int out = 2;

        if ( str1length == 0 || str2length == 0 ){
            throw new EmptyStringException();
        } 

        boolean neg1 = false, neg2 = false;
        int start1 = 0, start2 = 0;

        if( firstString.charAt(0) == '-' ){
            if ( str1length == 1 ) throw new NumberFormatException(firstString);
            neg1 = true;
            start1 = 1;
        } else if( firstString.charAt(0) == '+' ){
            if ( str1length == 1 ) throw new NumberFormatException(firstString);
            start1 = 1;
        }

        if( secondString.charAt(0) == '-' ){
            if ( str2length == 1 ) throw new NumberFormatException(secondString);
            neg2 = true;
            start2 = 1;
        } else if( secondString.charAt(0) == '+' ){
            if ( str2length == 1 ) throw new NumberFormatException(secondString);
            start2 = 1;
        }

        while( start1 < str1length && firstString.charAt(start1) == '0' ) start1++;
        while( start2 < str2length && secondString.charAt(start2) == '0' ) start2++;
        int runlen1 = str1length - start1,
            runlen2 = str2length - start2;

        if ( runlen1 == 0 ){
            if ( runlen2 == 0 ) out = 0;
            else if ( neg2 == true ) out = 1;
            else out = -1;
        } else if ( runlen2 == 0 ){
            if ( neg1 == true ) out = -1;
            else out = 1;
        }
 
        if ( out == 2 && neg1 != neg2 ){
            if ( neg1 == false ) out = 1;
            if ( neg1 == true ) out = -1;
        }
        
        int c1, c2;
        if ( out == 2 ){
            if ( neg1 == true ){
                if ( runlen1 > runlen2 ) out = -1;
                else if ( runlen1 < runlen2 ) out = 1;
 
                if ( out == 2 ){
                    while ( runlen1-- != 0 ){
                        c1 = firstString.charAt(start1++);
                        c2 = secondString.charAt(start2++);
                        
                        if ( c1 > 96 ) c1 -= 87;
                        else if ( c1 > 64 ) c1 -= 55;
                        else {
                            c1 ^= 48;
                            if ( c1 > 9 ) throw new NumberFormatException(firstString);
                        }
                        if ( !(c1 < base) ) throw new NumberFormatException(firstString);

                        if ( c2 > 96 ) c2 -= 87;
                        else if ( c2 > 64 ) c2 -= 55;
                        else {
                            c2 ^= 48;
                            if ( c2 > 9 ) throw new NumberFormatException(secondString);
                        }
                        if ( !(c2 < base) ) throw new NumberFormatException(secondString);
                        
                        if ( c1 < c2 ){
                            out = 1;
                            break;
                        } 
                        
                        if ( c1 > c2 ){
                            out = -1;
                            break;
                        }
                    }
                }
            } else if ( neg1 == false ){
                if ( runlen1 > runlen2 ) out = 1;
                else if ( runlen1 < runlen2 ) out = -1;
 
                if ( out == 2 ){
                    while( runlen1-- != 0 ){
                        c1 = firstString.charAt(start1++);
                        c2 = secondString.charAt(start2++);
                        
                        if ( c1 > 96 ) c1 -= 87;
                        else if ( c1 > 64 ) c1 -= 55;
                        else {
                            c1 ^= 48;
                            if ( c1 > 9 ) throw new NumberFormatException(firstString);
                        }
                        if ( !(c1 < base) ) throw new NumberFormatException(firstString);

                        if ( c2 > 96 ) c2 -= 87;
                        else if ( c2 > 64 ) c2 -= 55;
                        else {
                            c2 ^= 48;
                            if ( c2 > 9 ) throw new NumberFormatException(secondString);
                        }
                        if ( !(c2 < base) ) throw new NumberFormatException(secondString);
                        
                        if ( c1 > c2 ) {
                            out = 1;
                            break;
                        }
                        
                        if ( c1 < c2 ) {
                            out = -1;
                            break;
                        }
                    }
                }
            }
        }

        while ( start1 < str1length  ) {
            c1 = firstString.charAt(start1++);
            if ( c1 > 96 ) c1 -= 87;
            else if ( c1 > 64 ) c1 -= 55;
            else {
                c1 ^= 48;
                if ( c1 > 9 ) throw new NumberFormatException(firstString);
            }
            if ( !(c1 < base) ) throw new NumberFormatException(firstString);
        }
     
        while ( start2 < str2length ) {
            c2 = secondString.charAt(start2++);
            if ( c2 > 96 ) c2 -= 87;
            else if ( c2 > 64 ) c2 -= 55;
            else {
                c2 ^= 48;
                if ( c2 > 9 ) throw new NumberFormatException(secondString);
            }
            if ( !(c2 < base) ) throw new NumberFormatException(secondString);
        }

        if ( out == 2 ) return 0;
        return out;
    }
}
