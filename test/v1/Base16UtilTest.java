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
import host.fai.lib.faiNumber.Base16Util;
import host.fai.lib.faiNumber.IntUtil;
import host.fai.lib.faiNumber.LongUtil;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;
import java.util.Random;
/**
 * <p>The <code>Base16UtilTest</code> is a test unit for
 * {@code host.fai.lib.faiNumber.Base16Util}.
 *
 * @author  Khang Hoang Nguyen
 **/
final class Base16UtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        Base16UtilTestHelper.toInt();
        System.out.println(separator);
        Base16UtilTestHelper.toIntAsUnsigned();
        System.out.println(separator);
        Base16UtilTestHelper.toLong();
        System.out.println(separator);
        Base16UtilTestHelper.toLongAsUnsigned();
    }
}

final class Base16UtilTestHelper{
    public static void toInt(){
        long start, end, result;
        final String failMsg = "Base16Util.toInt() test fail.";
        System.out.println("=== Base16Util.toInt(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i != 0; i++){
            if( Base16Util.toInt(IntUtil.toSignedHex(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        for (int i = 0; i != Integer.MAX_VALUE; i++){
            if( Base16Util.toInt(IntUtil.toSignedHex(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        if( Base16Util.toInt(Integer.toHexString(Integer.MAX_VALUE)) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // Integer.MIN_VALUE with leading zeroes.
        if ( Base16Util.toInt("-0000000080000000") != -2147483648 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( Base16Util.toInt("+0000000007fffffff") != 2147483647 ) throw new RuntimeException(failMsg);
        // +/- 240s with leading zeroes.
        if ( Base16Util.toInt("-000f0") != -240 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toInt("+000f0") != 240 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toInt("000f0") != 240 ) throw new RuntimeException(failMsg);
        // Letter Equality 
        if ( Base16Util.toInt("0abcdef") != Base16Util.toInt("0ABCDEF") ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base16Util.toInt("-000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toInt("+000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toInt("000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toInt("+0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toInt("-0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toInt("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many.
        try{                 
            Base16Util.toInt("-80000001");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                 
            Base16Util.toInt("80000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{                 
            Base16Util.toInt("-800000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{                           
            Base16Util.toInt("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toInt("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toInt("@");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toInt("`");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toInt("abcdefg");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toInt("ABCDEFG");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base16Util.toInt("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toInt("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base16Util.toInt("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base16Util.toInt(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toIntAsUnsigned(){
        long start, end, result;
        final String failMsg = "Base16Util.toIntAsUnsigned() test fail.";
        System.out.println("=== Base16Util.toIntAsUnsigned(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i != 0; i++){
            if( Base16Util.toIntAsUnsigned(Integer.toHexString(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        for (int i = 0; i != Integer.MAX_VALUE; i++){
            if( Base16Util.toIntAsUnsigned(Integer.toHexString(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        if( Base16Util.toIntAsUnsigned(Integer.toHexString(Integer.MAX_VALUE)) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // 4294967295 unsigned or -1 signed with leading zeroes..
        if ( Base16Util.toIntAsUnsigned("00000000ffffffff") != -1 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( Base16Util.toIntAsUnsigned("0000000007fffffff") != 2147483647 ) throw new RuntimeException(failMsg);
        // 240 with leading zeroes.
        if ( Base16Util.toIntAsUnsigned("000f0") != 240 ) throw new RuntimeException(failMsg);
        // Letter equality.
        if ( Base16Util.toIntAsUnsigned("0abcdef") != Base16Util.toIntAsUnsigned("0ABCDEF") ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base16Util.toIntAsUnsigned("000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toIntAsUnsigned("000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toIntAsUnsigned("00") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toIntAsUnsigned("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many & digit too many.
        try{                            
            Base16Util.toIntAsUnsigned("100000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{                           
            Base16Util.toIntAsUnsigned("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toIntAsUnsigned("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toIntAsUnsigned("@");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toIntAsUnsigned("`");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toIntAsUnsigned("abcdefg");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base16Util.toIntAsUnsigned("ABCDEFg");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            Base16Util.toIntAsUnsigned("+01");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toIntAsUnsigned("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base16Util.toIntAsUnsigned("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base16Util.toIntAsUnsigned(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toLong(){
        long start, end, result;
        final String failMsg = "Base16Util.toLong() test fail.";
        System.out.println("=== Base16Util.toLong(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 1 billion numbers from Long.MIN_VALUE, 1 billion below zero, 1 billion above zero, and 1 billion below Long.MAX_VALUE.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 1000000000L; i++){
            if( Base16Util.toLong(LongUtil.toSignedHex(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done lowest range.");

        for (long i = Long.MAX_VALUE - 1000000000L; i != Long.MAX_VALUE; i++){
            if( Base16Util.toLong(LongUtil.toSignedHex(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        if( Base16Util.toLong(LongUtil.toSignedHex(Long.MAX_VALUE)) != Long.MAX_VALUE ) throw new RuntimeException(failMsg);

        System.out.println("Done higest range.");

        for (long i = 0 ; i > 0 - 1000000001L; i--){
            if( Base16Util.toLong(LongUtil.toSignedHex(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done 0 low range");

        for (long i = 0 ; i < 0 + 1000000001L; i++){
            if( Base16Util.toLong(LongUtil.toSignedHex(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done 0 high range");
        System.out.println("TEST 1: Passed.");
        System.out.println();System.out.println();System.out.println();


        System.out.println("TEST 2: Random parsing 100 million numbers.");
        Random gen = new Random();
        long number;
        for (int i = 0; i < 100000000; i++){
            number = gen.nextLong();
            if( Base16Util.toLong(LongUtil.toSignedHex(number)) != number ){
                System.out.println("Wrong " + number);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 2: Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing with leading zeroes.");
        // Long.MIN_VALUE with leading zeroes.
        if( Base16Util.toLong("-000008000000000000000") != -9223372036854775808L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( Base16Util.toLong("0007fffffffffffffff") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // +/- 17592186044400s with leading zeroes.
        if( Base16Util.toLong("-000000000000000000ffffffffff0") != -17592186044400L ) throw new RuntimeException(failMsg);
        if( Base16Util.toLong("+000000000000000000ffffffffff0") != 17592186044400L ) throw new RuntimeException(failMsg);
        if( Base16Util.toLong("000000000000000000ffffffffff0") != 17592186044400L ) throw new RuntimeException(failMsg);
        // Letter equality.
        if( Base16Util.toLong("0abcdef") != Base16Util.toLong("0ABCDEF") ) throw new RuntimeException(failMsg);  
        // Zeroes.
        if ( Base16Util.toLong("-000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toLong("+000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toLong("000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toLong("+0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toLong("-0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base16Util.toLong("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3: Passed.");
        System.out.println();System.out.println();System.out.println();
        System.out.println("TEST 4: Parsing invalid format");

        // 1 too many.
        try{                   
            Base16Util.toLong("-8000000000000001");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{               
            Base16Util.toLong("8000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many,
        try{                   
            Base16Util.toLong("-80000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            Base16Util.toLong("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLong("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLong("@");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLong("`");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLong("abcdefg");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLong("ABCDEFG");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base16Util.toLong("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLong("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base16Util.toLong("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4: Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base16Util.toLong(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toLongAsUnsigned(){
        long start, end, result;
        final String failMsg = "Base16Util.toLongAsUnsigned() test fail.";
        System.out.println("=== Base16Util.toLongAsUnsigned(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 1 billion numbers from Long.MIN_VALUE, 1 billion below zero, 1 billion above zero, and 1 billion below Long.MAX_VALUE.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 1000000000L; i++){
            if( Base16Util.toLongAsUnsigned(Long.toHexString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done lowest range.");

        for (long i = Long.MAX_VALUE - 1000000000L; i != Long.MAX_VALUE; i++){
            if( Base16Util.toLongAsUnsigned(Long.toHexString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        if( Base16Util.toLongAsUnsigned(Long.toHexString(Long.MAX_VALUE)) != Long.MAX_VALUE ) throw new RuntimeException(failMsg);

        System.out.println("Done higest range.");

        for (long i = 0 ; i > 0 - 1000000000L - 1L; i--){
            if( Base16Util.toLongAsUnsigned(Long.toHexString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done 0 low range");

        for (long i = 0 ; i < 0 + 1000000001L; i++){
            if( Base16Util.toLongAsUnsigned(Long.toHexString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done 0 high range");
        System.out.println("TEST 1: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Random parsing 100 million numbers.");
        Random gen = new Random();
        long number;
        for (int i = 0; i < 100000000; i++){
            number = gen.nextLong();
            if( Base16Util.toLongAsUnsigned(Long.toHexString(number)) != number ){
                System.out.println("Wrong " + number);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 2: Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing with leading zeroes.");
        // 18446744073709551615L unsigned or -1L signed with leading zeroes.
        if( Base16Util.toLongAsUnsigned("0000ffffffffffffffff") != -1L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( Base16Util.toLongAsUnsigned("007fffffffffffffff") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // 17592186044400 with leading zeroes.
        if( Base16Util.toLongAsUnsigned("000000000000000000ffffffffff0") != 17592186044400L ) throw new RuntimeException(failMsg);
        // Letter equality.
        if( Base16Util.toLongAsUnsigned("0abcdef") != Base16Util.toLongAsUnsigned("0ABCDEF") ) throw new RuntimeException(failMsg);
        // Zeroes.
        if( Base16Util.toLongAsUnsigned("0000000") != 0L ) throw new RuntimeException(failMsg);
        if( Base16Util.toLongAsUnsigned("0") != 0L ) throw new RuntimeException(failMsg);
        if( Base16Util.toLongAsUnsigned("00000") != 0L ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 4: Parsing invalid format");
        // 1 too many & 1 digit too many.
        try{                              
            Base16Util.toLongAsUnsigned("10000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            Base16Util.toLongAsUnsigned("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLongAsUnsigned("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLongAsUnsigned("@");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLongAsUnsigned("`");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLongAsUnsigned("abcdefg");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLongAsUnsigned("ABCDEFG");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            Base16Util.toLongAsUnsigned("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base16Util.toLongAsUnsigned("+01");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base16Util.toLongAsUnsigned("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4: Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base16Util.toLongAsUnsigned(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
}
