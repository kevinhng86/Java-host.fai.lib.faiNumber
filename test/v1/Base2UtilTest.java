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
import host.fai.lib.faiNumber.Base2Util;
import host.fai.lib.faiNumber.IntUtil;
import host.fai.lib.faiNumber.LongUtil;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;
import java.util.Random;
/**
 * <p>The <code>Base2UtilTest</code> is a test unit for
 * {@code host.fai.lib.faiNumber.Base2Util}.
 *
 * @author  Khang Hoang Nguyen
 **/
final class Base2UtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        Base2UtilTestHelper.toInt();
        System.out.println(separator);
        Base2UtilTestHelper.toIntAsUnsigned();
        System.out.println(separator);
        Base2UtilTestHelper.toLong();
        System.out.println(separator);
        Base2UtilTestHelper.toLongAsUnsigned();
    }
}

final class Base2UtilTestHelper{
    public static void toInt(){
        long start, end, result;
        final String failMsg = "Base2Util.toInt() test fail.";
        System.out.println("=== Base2Util.toInt(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i != 0; i++){
            if( Base2Util.toInt(IntUtil.toSignedBinary(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        for (int i = 0; i != Integer.MAX_VALUE; i++){
            if( Base2Util.toInt(IntUtil.toSignedBinary(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        if( Base2Util.toInt(Integer.toBinaryString(Integer.MAX_VALUE)) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // Integer.MIN_VALUE with leading zeroes.
        if ( Base2Util.toInt("-0000000010000000000000000000000000000000") != -2147483648 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( Base2Util.toInt("+0000000001111111111111111111111111111111") != 2147483647 ) throw new RuntimeException(failMsg);
        // +/- 10s with leading zeroes.
        if ( Base2Util.toInt("0001010") != 10 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toInt("-0001010") != -10 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toInt("+0001010") != 10 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base2Util.toInt("-000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toInt("+000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toInt("000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toInt("+0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toInt("-0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toInt("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many.
        try{                  
            Base2Util.toInt("-10000000000000000000000000000001");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                 
            Base2Util.toInt("10000000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{                 
            Base2Util.toInt("-100000000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{                           
            Base2Util.toInt("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
 
        try{                           
            Base2Util.toInt("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{                           
            Base2Util.toInt("111111111112");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base2Util.toInt("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base2Util.toInt("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base2Util.toInt("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base2Util.toInt(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toIntAsUnsigned(){
        long start, end, result;
        final String failMsg = "Base2Util.toIntAsUnsigned() test fail.";
        System.out.println("=== Base2Util.toIntAsUnsigned(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i != 0; i++){
            if( Base2Util.toIntAsUnsigned(Integer.toBinaryString(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        for (int i = 0; i != Integer.MAX_VALUE; i++){
            if( Base2Util.toIntAsUnsigned(Integer.toBinaryString(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        if( Base2Util.toIntAsUnsigned(Integer.toBinaryString(Integer.MAX_VALUE)) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // 4294967295 unsigned or -1 signed with leading zeroes.
        if ( Base2Util.toIntAsUnsigned("0000000011111111111111111111111111111111") != -1 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( Base2Util.toIntAsUnsigned("0000000001111111111111111111111111111111") != 2147483647 ) throw new RuntimeException(failMsg);
        // 10 with leading zeroes.
        if ( Base2Util.toIntAsUnsigned("0001010") != 10 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base2Util.toIntAsUnsigned("000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toIntAsUnsigned("000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toIntAsUnsigned("00") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toIntAsUnsigned("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many & 1 digit too many.
        try{                           
            Base2Util.toIntAsUnsigned("100000000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{                           
            Base2Util.toIntAsUnsigned("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{                           
            Base2Util.toIntAsUnsigned("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{                           
            Base2Util.toIntAsUnsigned("111111111112");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            Base2Util.toIntAsUnsigned("+01");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base2Util.toIntAsUnsigned("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base2Util.toIntAsUnsigned("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base2Util.toIntAsUnsigned(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toLong(){
        long start, end, result;
        final String failMsg = "Base2Util.toLong() test fail.";
        System.out.println("=== Base2Util.toLong(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 1 billion numbers from Long.MIN_VALUE, 1 billion below zero, 1 billion above zero, and 1 billion below Long.MAX_VALUE.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 1000000000L; i++){
            if( Base2Util.toLong(LongUtil.toSignedBinary(i)) != i ) throw new RuntimeException(failMsg);
        }

        System.out.println("Done lowest range.");

        for (long i = Long.MAX_VALUE - 1000000000L; i != Long.MAX_VALUE; i++){
            if( Base2Util.toLong(LongUtil.toSignedBinary(i)) != i ) {
                System.out.println("Wrong " + i); 
                throw new RuntimeException(failMsg);
            }
        }

        if( Base2Util.toLong(LongUtil.toSignedBinary(Long.MAX_VALUE)) != Long.MAX_VALUE ) throw new RuntimeException(failMsg);

        System.out.println("Done higest range.");

        for (long i = 0 ; i > 0 - 1000000001L; i--){
            if( Base2Util.toLong(LongUtil.toSignedBinary(i)) != i ) {
                System.out.println("Wrong " + i); 
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done 0 low range");

        for (long i = 0 ; i < 0 + 1000000001L; i++){
            if( Base2Util.toLong(LongUtil.toSignedBinary(i)) != i ) {
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
            if( Base2Util.toLong(LongUtil.toSignedBinary(number)) != number){
                System.out.println("Wrong" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 2: Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing with leading zeroes.");
        // Long.MIN_VALUE with leading zeroes.
        if( Base2Util.toLong("-00000001000000000000000000000000000000000000000000000000000000000000000") != -9223372036854775808L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( Base2Util.toLong("000111111111111111111111111111111111111111111111111111111111111111") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // +/- 80000000000500 with leading zeroes.
        if( Base2Util.toLong("-00000000000000000010010001100001001110011100101010000000111110100") != -80000000000500L ) throw new RuntimeException(failMsg);
        if( Base2Util.toLong("+00000000000000000010010001100001001110011100101010000000111110100") != 80000000000500L ) throw new RuntimeException(failMsg);
        if( Base2Util.toLong("00000000000000000010010001100001001110011100101010000000111110100") != 80000000000500L ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( Base2Util.toLong("-000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toLong("+000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toLong("000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toLong("+0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toLong("-0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base2Util.toLong("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 4: Parsing invalid format");

        // 1 too many.
        try{                  
            Base2Util.toLong("-1000000000000000000000000000000000000000000000000000000000000001");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{               
            Base2Util.toLong("1000000000000000000000000000000000000000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many,
        try{                   
            Base2Util.toLong("-10000000000000000000000000000000000000000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            Base2Util.toLong("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base2Util.toLong("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base2Util.toLong("11111111111112");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base2Util.toLong("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base2Util.toLong("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base2Util.toLong("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4: Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base2Util.toLong(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toLongAsUnsigned(){
        long start, end, result;
        final String failMsg = "Base2Util.toLongAsUnsigned() test fail.";
        System.out.println("=== Base2Util.toLongAsUnsigned(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 1 billion numbers from Long.MIN_VALUE, 1 billion below zero, 1 billion above zero, and 1 billion below Long.MAX_VALUE.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 1000000000L; i++){
            if( Base2Util.toLongAsUnsigned(Long.toBinaryString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done lowest range.");

        for (long i = Long.MAX_VALUE - 1000000000L; i != Long.MAX_VALUE; i++){
            if( Base2Util.toLongAsUnsigned(Long.toBinaryString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        if( Base2Util.toLongAsUnsigned(Long.toBinaryString(Long.MAX_VALUE)) != Long.MAX_VALUE ) throw new RuntimeException(failMsg);

        System.out.println("Done higest range.");

        for (long i = 0 ; i > 0 - 1000000001L; i--){
            if( Base2Util.toLongAsUnsigned(Long.toBinaryString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done 0 low range");

        for (long i = 0 ; i < 0 + 1000000001L; i++){
            if( Base2Util.toLongAsUnsigned(Long.toBinaryString(i)) != i ) {
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
            if( Base2Util.toLongAsUnsigned(Long.toBinaryString(number)) != number ){
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 2: Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing with leading zeroes.");
        // 18446744073709551615 unsigned or -1L signed with leading zeroes.
        if( Base2Util.toLongAsUnsigned("00001111111111111111111111111111111111111111111111111111111111111111") != -1L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( Base2Util.toLongAsUnsigned("00111111111111111111111111111111111111111111111111111111111111111") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // 80000000000500 with leading zeroes.
        if( Base2Util.toLongAsUnsigned("00000000000000000010010001100001001110011100101010000000111110100") != 80000000000500L ) throw new RuntimeException(failMsg);
        // Zeroes.
        if( Base2Util.toLongAsUnsigned("0000000") != 0L ) throw new RuntimeException(failMsg);
        if( Base2Util.toLongAsUnsigned("0") != 0L ) throw new RuntimeException(failMsg);
        if( Base2Util.toLongAsUnsigned("00000") != 0L ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 4: Parsing invalid format");
        // 1 too many & 1 digit too many.
        try{
            Base2Util.toLongAsUnsigned("10000000000000000000000000000000000000000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            Base2Util.toLongAsUnsigned("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base2Util.toLongAsUnsigned("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base2Util.toLongAsUnsigned("11111112");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            Base2Util.toLongAsUnsigned("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base2Util.toLongAsUnsigned("+01");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base2Util.toLongAsUnsigned("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4: Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base2Util.toLongAsUnsigned(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
}
