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
import host.fai.lib.faiNumber.Base3Util;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;
import java.util.Random;
/**
 * <p>The <code>Base3UtilTest</code> is a test unit for
 * {@code host.fai.lib.faiNumber.Base3Util}.
 *
 * @author  Khang Hoang Nguyen
 **/
final class Base3UtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        Base3UtilTestHelper.toInt();
        System.out.println(separator);
        Base3UtilTestHelper.toIntAsUnsigned();
        System.out.println(separator);
        Base3UtilTestHelper.toLong();
        System.out.println(separator);
        Base3UtilTestHelper.toLongAsUnsigned();
    }
}

final class Base3UtilTestHelper{
    public static void toInt(){
        long start, end, result;
        final String failMsg = "Base3Util.toInt() test fail.";
        System.out.println("=== Base3Util.toInt(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 50 million numbers.");
        for (int i = Integer.MIN_VALUE; i != Integer.MIN_VALUE + 10000000; i++){
            if( Base3Util.toInt(TestHelperTo.signed(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done low range.");
        for (int i = 0; i != 0 - 10000000; i--){
            if( Base3Util.toInt(TestHelperTo.signed(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done low 0.");
        for (int i = 0; i != 0 + 10000000; i++){
            if( Base3Util.toInt(TestHelperTo.signed(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done high 0.");
        for (int i = Integer.MAX_VALUE; i != Integer.MAX_VALUE - 10000000; i--){
            if( Base3Util.toInt(TestHelperTo.signed(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done high range.");
        Random r = new Random();
        int num; 
        for (int i = 0; i < 10000000; i++){
            num = r.nextInt(Integer.MAX_VALUE);
            if( Base3Util.toInt(TestHelperTo.signed(num, 3)) != num ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }            
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // Integer.MIN_VALUE with leading zeroes.
        if ( Base3Util.toInt("-0000000012112122212110202102") != -2147483648 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( Base3Util.toInt("+00000000012112122212110202101") != 2147483647 ) throw new RuntimeException(failMsg);
        // +/- 702s with leading zeroes.
        if ( Base3Util.toInt("000222000") != 702 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toInt("-00222000") != -702 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toInt("+0000222000") != 702 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base3Util.toInt("-000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toInt("+000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toInt("000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toInt("+0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toInt("-0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toInt("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many.
        try{                 
            Base3Util.toInt("-12112122212110202110");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                 
            Base3Util.toInt("12112122212110202102");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{                 
            Base3Util.toInt("-121121222121102021010");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{                           
            Base3Util.toInt("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base3Util.toInt("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{                           
            Base3Util.toInt("1211212221211032");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base3Util.toInt("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base3Util.toInt("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base3Util.toInt("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base3Util.toInt(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toIntAsUnsigned(){
        long start, end, result;
        final String failMsg = "Base3Util.toIntAsUnsigned() test fail.";
        System.out.println("=== Base3Util.toIntAsUnsigned(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 50 million numbers.");
        for (int i = Integer.MIN_VALUE; i != Integer.MIN_VALUE + 10000000; i++){
            if( Base3Util.toIntAsUnsigned(TestHelperTo.unsigned(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done low range.");
        for (int i = 0; i != 0 - 10000000; i--){
            if( Base3Util.toIntAsUnsigned(TestHelperTo.unsigned(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done low 0.");
        for (int i = 0; i != 0 + 10000000; i++){
            if( Base3Util.toIntAsUnsigned(TestHelperTo.unsigned(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done high 0.");
        for (int i = Integer.MAX_VALUE; i != Integer.MAX_VALUE - 10000000; i--){
            if( Base3Util.toIntAsUnsigned(TestHelperTo.unsigned(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done high range.");
        Random r = new Random();
        int num; 
        for (int i = 0; i < 10000000; i++){
            num = r.nextInt(Integer.MAX_VALUE);
            if( Base3Util.toIntAsUnsigned(TestHelperTo.unsigned(num, 3)) != num ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }            
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // 4294967295 as unsigned or -1 as signed with leading 0.
        if ( Base3Util.toIntAsUnsigned("0000000102002022201221111210") != -1 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( Base3Util.toIntAsUnsigned("00000000012112122212110202101") != 2147483647 ) throw new RuntimeException(failMsg);
        // 702s with leading zeroes.
        if ( Base3Util.toIntAsUnsigned("0000222000") != 702 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toIntAsUnsigned("000222000") != 702 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toIntAsUnsigned("00222000") != 702 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base3Util.toIntAsUnsigned("000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toIntAsUnsigned("000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toIntAsUnsigned("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many.
        try{                 
            Base3Util.toIntAsUnsigned("102002022201221111211");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{                 
            Base3Util.toIntAsUnsigned("1020020222012211112101");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{                           
            Base3Util.toIntAsUnsigned("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base3Util.toIntAsUnsigned("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base3Util.toIntAsUnsigned("102002022201322111");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            Base3Util.toIntAsUnsigned("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base3Util.toIntAsUnsigned("+0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base3Util.toIntAsUnsigned("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base3Util.toIntAsUnsigned(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toLong(){
        long start, end, result;
        final String failMsg = "Base3Util.toLong() test fail.";
        System.out.println("=== Base3Util.toLong(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 50 million numbers.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 10000000L; i++){
            if( Base3Util.toLong(TestHelperTo.signed(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done low range.");
        for (long i = 0L; i != 0L - 10000000; i--){
            if( Base3Util.toLong(TestHelperTo.signed(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done low 0.");
        for (long i = 0L; i != 0L + 10000000L; i++){
            if( Base3Util.toLong(TestHelperTo.signed(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done high 0.");
        for (long i = Long.MAX_VALUE; i != Long.MAX_VALUE - 10000000L; i--){
            if( Base3Util.toLong(TestHelperTo.signed(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done high range.");
        Random r = new Random();
        long num; 
        for (int i = 0; i < 10000000; i++){
            num = r.nextLong();
            if( Base3Util.toLong(TestHelperTo.signed(num, 3)) != num ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }            
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // Long.MIN_VALUE with leading zeroes.
        if ( Base3Util.toLong("-000000002021110011022210012102010021220101220222") != -9223372036854775808L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if ( Base3Util.toLong("+0000000002021110011022210012102010021220101220221") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // +/- 9223372036854 with leading zeroes.
        if ( Base3Util.toLong("00001012122202010122201000001100") != 9223372036854L ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLong("-001012122202010122201000001100") != -9223372036854L ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLong("+0001012122202010122201000001100") != 9223372036854L ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base3Util.toLong("-000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLong("+000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLong("000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLong("+0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLong("-0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLong("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many.
        try{                 
            Base3Util.toLong("-2021110011022210012102010021220101221000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                 
            Base3Util.toLong("2021110011022210012102010021220101220222");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{                 
            Base3Util.toLong("-20211100110222100121020100212201012202210");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{                           
            Base3Util.toLong("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{                           
            Base3Util.toLong("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{                           
            Base3Util.toLong("202111001102223");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base3Util.toLong("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base3Util.toLong("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base3Util.toLong("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base3Util.toLong(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toLongAsUnsigned(){
        long start, end, result;
        final String failMsg = "Base3Util.toLongAsUnsigned() test fail.";
        System.out.println("=== Base3Util.toLongAsUnsigned(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 50 million numbers.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 10000000L; i++){
            if( Base3Util.toLongAsUnsigned(TestHelperTo.unsigned(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done low range.");
        for (long i = 0L; i != 0L - 10000000; i--){
            if( Base3Util.toLongAsUnsigned(TestHelperTo.unsigned(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done low 0.");
        for (long i = 0L; i != 0L + 10000000L; i++){
            if( Base3Util.toLongAsUnsigned(TestHelperTo.unsigned(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done high 0.");
        for (long i = Long.MAX_VALUE; i != Long.MAX_VALUE - 10000000L; i--){
            if( Base3Util.toLongAsUnsigned(TestHelperTo.unsigned(i, 3)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done high range.");
        Random r = new Random();
        long num; 
        for (int i = 0; i < 10000000; i++){
            num = r.nextLong();
            if( Base3Util.toLongAsUnsigned(TestHelperTo.unsigned(num, 3)) != num ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }            
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // 18446744073709551615 as unsigned or -1 as signed with leading zero.                                                 
        if ( Base3Util.toLongAsUnsigned("0000000011112220022122120101211020120210210211220") != -1L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if ( Base3Util.toLongAsUnsigned("0000000002021110011022210012102010021220101220221") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // 9223372036854  with leading zeroes.
        if ( Base3Util.toLongAsUnsigned("00001012122202010122201000001100") != 9223372036854L ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base3Util.toLongAsUnsigned("000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLongAsUnsigned("0000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base3Util.toLongAsUnsigned("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many.
        try{                 
            Base3Util.toLongAsUnsigned("11112220022122120101211020120210210211221");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{                 
            Base3Util.toLongAsUnsigned("111122200221221201012110201202102102112200");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{                           
            Base3Util.toLongAsUnsigned("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base3Util.toLongAsUnsigned("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{                           
            Base3Util.toLongAsUnsigned("11112220022122123");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            Base3Util.toLongAsUnsigned("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base3Util.toLongAsUnsigned("+1");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base3Util.toLongAsUnsigned("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base3Util.toLongAsUnsigned(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
}

final class TestHelperTo{
    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                          'a', 'b', 'c', 'd', 'e', 'f', 'h', 'i', 'j', 'k',
                                          'l', 'm', 'n', 'o', 'p', 'w', 'x', 'y', 'z'}; 
    
    public static String unsigned(int input, final int base){
        if ( base < 2 || base > 36 ) throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        if ( input == 0 ) return "0";
        final char[] out = new char[32];
        final int baseLess1 = base - 1;
        int pos = 32, shift, quotient, remainder;
    
        while ( input != 0 ){            
            quotient = 0; 
            remainder = 0;
            for ( int i = 31; i > -1; i-- ){ 
                remainder <<= 1;
                shift = (1 << i);
            
                if ( (input & shift ) != 0 ) remainder |= 1;
            
                if (remainder > baseLess1) {
                    remainder = remainder - base;
                    quotient |= shift;
                }
            }
            
            out[--pos] = digits[remainder];
            input = quotient;
        }
        
        return new String(out, pos, 32 - pos);
    }

    public static String unsigned(long input, final int base){
        if ( base < 2 || base > 36 ) throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        if ( input == 0L ) return "0";
        final char[] out = new char[64];
        final int baseLess1 = base - 1;
        int pos = 64, remainder;
        long shift, quotient;
    
        while ( input != 0L ){            
            quotient = 0L; 
            remainder = 0;
            for ( int i = 63; i > -1; i-- ){ 
                remainder <<= 1;
                shift = (1L << i);
            
                if ( (input & shift ) != 0 ) remainder |= 1;
            
                if (remainder > baseLess1) {
                    remainder = remainder - base;
                    quotient |= shift;
                }
            }
            
            out[--pos] = digits[remainder];
            input = quotient;
        }
        
        return new String(out, pos, 64 - pos);
    }

    public static String signed(int input, final int base){
        if ( base < 2 || base > 36 ) throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        if ( input == 0 ) return "0";
        final char[] out = new char[33];
        final int baseLess1 = base - 1;
        int pos = 33, shift, quotient, remainder;
        boolean neg = false;
        
        if ( input < 0 ) {
            input = ~input + 1;
            neg = true;
        }

        while ( input != 0 ){            
            quotient = 0; 
            remainder = 0;
            for ( int i = 31; i > -1; i-- ){ 
                remainder <<= 1;
                shift = (1 << i);
            
                if ( (input & shift ) != 0 ) remainder |= 1;
            
                if (remainder > baseLess1) {
                    remainder = remainder - base;
                    quotient |= shift;
                }
            }
            
            out[--pos] = digits[remainder];
            input = quotient;
        }
        if ( neg == true ) out[--pos] = '-';
        return new String(out, pos, 33 - pos);
    }
    
    public static String signed(long input, final int base){
        if ( base < 2 || base > 36 ) throw new UnsupportedOperationException("Unsupported base. Input base: " + base + ".");
        if ( input == 0L ) return "0";
        final char[] out = new char[65];
        final int baseLess1 = base - 1;
        int pos = 65, remainder;
        long shift, quotient;
        boolean neg = false;
        
        if ( input < 0L ) {
            input = ~input + 1L;
            neg = true;
        }

        while ( input != 0L ){            
            quotient = 0L; 
            remainder = 0;
            for ( int i = 63; i > -1; i-- ){ 
                remainder <<= 1;
                shift = (1L << i);
            
                if ( (input & shift ) != 0 ) remainder |= 1;
            
                if (remainder > baseLess1) {
                    remainder = remainder - base;
                    quotient |= shift;
                }
            }
            
            out[--pos] = digits[remainder];
            input = quotient;
        }
        if ( neg == true ) out[--pos] = '-';
        return new String(out, pos, 65 - pos);
    }
}
