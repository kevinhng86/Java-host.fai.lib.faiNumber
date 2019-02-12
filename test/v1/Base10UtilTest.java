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
import host.fai.lib.faiNumber.Base10Util;
import host.fai.lib.faiNumber.IntUtil;
import host.fai.lib.faiNumber.LongUtil;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;
import java.util.Random;

/**
 * <p>The <code>Base10UtilTest</code> is a test unit for
 * {@code host.fai.lib.faiNumber.Base10Util}
 *
 * @author  Khang Hoang Nguyen
 **/
final class Base10UtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        
        Base10UtilTestHelper.toInt();
        System.out.println(separator);
        Base10UtilTestHelper.toIntTrueError();
        System.out.println(separator);
        Base10UtilTestHelper.toLong();
        System.out.println(separator);
        Base10UtilTestHelper.toLongTrueError();
        System.out.println(separator);
        Base10UtilTestHelper.compareAsInt();  
        System.out.println(separator);
        Base10UtilTestHelper.intOrSmaller();      
        System.out.println(separator);
        Base10UtilTestHelper.compareAsLong();
        System.out.println(separator);
        Base10UtilTestHelper.longOrSmaller();
        System.out.println(separator);
        Base10UtilTestHelper.toIntAsUnsigned();
        System.out.println(separator);        
        Base10UtilTestHelper.toLongAsUnsigned();
    }
}

final class Base10UtilTestHelper{
    public static void toInt(){
        long start, end, result;
        final String failMsg = "Base10Util.toInt() test fail.";
        System.out.println("=== Base10Util.toInt(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i != 0; i++){
            if( Base10Util.toInt(""+i) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        for (int i = 0; i != Integer.MAX_VALUE; i++){
            if( Base10Util.toInt(""+i) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        if( Base10Util.toInt(""+Integer.MAX_VALUE) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // Integer.MIN_VALUE with leading zeroes.
        if ( Base10Util.toInt("-00000000000002147483648") != -2147483648 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( Base10Util.toInt("000000002147483647") != 2147483647 ) throw new RuntimeException(failMsg);
        // +/- 12300 with leading zeroes.
        if ( Base10Util.toInt("0000012300") != 12300 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toInt("+0000012300") != 12300 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toInt("-0000012300") != -12300 ) throw new RuntimeException(failMsg);
        // Zeroes
        if ( Base10Util.toInt("-000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toInt("+000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toInt("+0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toInt("-0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many.
        try{
            Base10Util.toInt("-2147483649");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toInt("2147483648");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{
            Base10Util.toInt("-21474836480");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toInt("21474836470");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            Base10Util.toInt("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toInt("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toInt(":");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toInt("12345a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base10Util.toInt("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toInt("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base10Util.toInt("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.toInt(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void toIntTrueError(){
        long start, end, result;
        final String failMsg = "Base10Util.toIntTrueError() test fail.";
        System.out.println("=== Base10Util.toIntTrueError(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i != 0; i++){
            if( Base10Util.toIntTrueError(""+i) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Halfway done.");
        for (int i = 0; i != Integer.MAX_VALUE; i++){
            if( Base10Util.toIntTrueError(""+i) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        if( Base10Util.toIntTrueError(""+Integer.MAX_VALUE) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // Integer.MIN_VALUE with leading zeroes.
        if ( Base10Util.toIntTrueError("-00000000000002147483648") != -2147483648 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( Base10Util.toIntTrueError("000000002147483647") != 2147483647 ) throw new RuntimeException(failMsg);
        // +/- 12300 with leading zeroes.
        if ( Base10Util.toIntTrueError("0000012300") != 12300 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toIntTrueError("+0000012300") != 12300 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toIntTrueError("-0000012300") != -12300 ) throw new RuntimeException(failMsg);
        // Zeroes
        if ( Base10Util.toIntTrueError("-000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toIntTrueError("+000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toIntTrueError("+0") != 0 ) throw new RuntimeException(failMsg);
        if ( Base10Util.toIntTrueError("-0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // Underflow
        try{
            Base10Util.toIntTrueError("-2147483649");
            throw new RuntimeException(failMsg);
        } catch (NumberUnderFlowException e){}

        try{
            Base10Util.toIntTrueError("-21474836480");
            throw new RuntimeException(failMsg);
        } catch (NumberUnderFlowException e){}

        try{
            Base10Util.toIntTrueError("-2000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberUnderFlowException e){}

        try{
            Base10Util.toIntTrueError("-299999999999999999999999");
            throw new RuntimeException(failMsg);
        } catch (NumberUnderFlowException e){}

        // Overflow
        try{
            Base10Util.toIntTrueError("2147483648");
            throw new RuntimeException(failMsg);
        } catch (NumberOverFlowException e){}

        try{
            Base10Util.toIntTrueError("21474836470");
            throw new RuntimeException(failMsg);
        } catch (NumberOverFlowException e){}

        try{
            Base10Util.toIntTrueError("29999999999999999999");
            throw new RuntimeException(failMsg);
        } catch (NumberOverFlowException e){}

        try{
            Base10Util.toIntTrueError("200000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberOverFlowException e){}


        // Incorrect digit.
        try{
            Base10Util.toIntTrueError("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toIntTrueError("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toIntTrueError("21474878364812345678912345:");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toIntTrueError("214748783648123456789123451a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base10Util.toIntTrueError("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toIntTrueError("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base10Util.toIntTrueError("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();
        
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.toIntTrueError(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void toLong(){
        long start, end, result;
        final String failMsg = "Base10Util.toLong() test fail.";
        System.out.println("=== Base10Util.toLong(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 1 billion numbers from Long.MIN_VALUE, 1 billion below zero, 1 billion above zero, and 1 billion below Long.MAX_VALUE.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 1000000000L; i++){
            if( Base10Util.toLong(""+i) != i ){
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }
        
        System.out.println("Done lowest range.");

        for (long i = Long.MAX_VALUE - 1000000000L; i != Long.MAX_VALUE; i++){
            if( Base10Util.toLong(""+i) != i ){
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        if( Base10Util.toLong(""+Long.MAX_VALUE) != Long.MAX_VALUE ) throw new RuntimeException(failMsg);

        System.out.println("Done higest range.");

        for (long i = 0 ; i > 0 - 1000000001L; i--){
            if( Base10Util.toLong(""+i) != i ){
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done 0 low range");

        for (long i = 0 ; i < 0 + 1000000001L; i++){
            if( Base10Util.toLong(""+i) != i ){
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
            if( Base10Util.toLong(""+number) != number ) {
                System.out.println("Wrong " + number);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 2: Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing with leading zeroes.");
        // Long.MIN_VALUE with leading zeroes.
        if( Base10Util.toLong("-00000000000009223372036854775808") != -9223372036854775808L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( Base10Util.toLong("0000000009223372036854775807") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // +/- 550000000000L with leading zeroes.
        if( Base10Util.toLong("000000550000000000") != 550000000000L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLong("+000000550000000000") != 550000000000L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLong("-000000550000000000") != -550000000000L ) throw new RuntimeException(failMsg);
        // Zeroes.  
        if( Base10Util.toLong("+0000000") != 0L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLong("-00") != 0L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLong("0000000") != 0L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLong("+0") != 0L ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 4: Parsing invalid format");
        // 1 too many.
        try{
            Base10Util.toLong("-9223372036854775809");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLong("9223372036854775808");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{
            Base10Util.toLong("-92233720368547758080");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLong("92233720368547758070");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            Base10Util.toLong("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLong("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLong(":");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLong("92233720a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base10Util.toLong("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLong("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base10Util.toLong("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4: Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.toLong(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void toLongTrueError(){
        long start, end, result;
        final String failMsg = "Base10Util.toLongTrueError() test fail.";
        System.out.println("=== Base10Util.toLongTrueError(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 1 billion numbers from Long.MIN_VALUE, 1 billion below zero, 1 billion above zero, and 1 billion below Long.MAX_VALUE.");

        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 1000000000L; i++){
            if( Base10Util.toLongTrueError(""+i) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done lowest range.");

        for (long i = Long.MAX_VALUE - 1000000000L; i != Long.MAX_VALUE; i++){
            if( Base10Util.toLongTrueError(""+i) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        if( Base10Util.toLongTrueError(""+Long.MAX_VALUE) != Long.MAX_VALUE ) throw new RuntimeException(failMsg);

        System.out.println("Done higest range.");

        for (long i = 0 ; i > 0 - 1000000001L; i--){
            if( Base10Util.toLongTrueError(""+i) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done 0 low range");

        for (long i = 0 ; i < 0 + 1000000001L; i++){
            if( Base10Util.toLongTrueError(""+i) != i ) {
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
            if( Base10Util.toLongTrueError(""+number) != number ) {
                System.out.println("Wrong " + number);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 2: Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing with leading zeroes.");
        // Long.MIN_VALUE with leading zeroes.
        if( Base10Util.toLongTrueError("-00000000000009223372036854775808") != -9223372036854775808L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( Base10Util.toLongTrueError("0000000009223372036854775807") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // +/- 550000000000L with leading zeroes.
        if( Base10Util.toLongTrueError("000000550000000000") != 550000000000L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLongTrueError("+000000550000000000") != 550000000000L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLongTrueError("-000000550000000000") != -550000000000L ) throw new RuntimeException(failMsg);
        // Zeroes.  
        if( Base10Util.toLongTrueError("+0000000") != 0L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLongTrueError("-00") != 0L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLongTrueError("0000000") != 0L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLongTrueError("+0") != 0L ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 4: Parsing invalid format");
        // Underflow
        try{
            Base10Util.toLongTrueError("-9223372036854775809");
            throw new RuntimeException(failMsg);
        } catch (NumberUnderFlowException e){}

        try{
            Base10Util.toLongTrueError("-92233720368547758080");
            throw new RuntimeException(failMsg);
        } catch (NumberUnderFlowException e){}

        try{
            Base10Util.toLongTrueError("-92222222222222222222222222222222222222");
            throw new RuntimeException(failMsg);
        } catch (NumberUnderFlowException e){}

        // Overflow
        try{
            Base10Util.toLongTrueError("9223372036854775808");
            throw new RuntimeException(failMsg);
        } catch (NumberOverFlowException e){}
        
        
        try{
            Base10Util.toLongTrueError("92233720368547758070");
            throw new RuntimeException(failMsg);
        } catch (NumberOverFlowException e){}

        try{
            Base10Util.toLongTrueError("92222222222222222222222222222222222222222");
            throw new RuntimeException(failMsg);
        } catch (NumberOverFlowException e){}

        // Incorrect digits.
        try{
            Base10Util.toLongTrueError("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongTrueError("12345649879796546589789797987/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongTrueError("1000000000000000000000000000000000000000000000a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongTrueError("-1000000000000000000000000000000000000000000:");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs.
        try{
            Base10Util.toLongTrueError("-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongTrueError("+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        // Empty string.
        try{
            Base10Util.toLongTrueError("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4: Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.toLongTrueError(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void compareAsInt(){
        long start, end, result;
        final String failMsg = "Base10Util.compareAsInt() test fail.";
        System.out.println("=== Base10Util.compareAsInt(String firstNumber, String secondNumber) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Right format comparision.");
        // 1
        if ( Base10Util.compareAsInt("23", "0") != 1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("23", "-0") != 1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("8000", "350") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("8000", "7999") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("800500000", "15895875") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("8000", "-8001") != 1  ) throw new RuntimeException(failMsg);
        if ( Base10Util.compareAsInt("2147483647", "-2147483648") != 1  ) throw new RuntimeException(failMsg);
        // Equal
        if ( Base10Util.compareAsInt("23", "+23") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("-23", "-23") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("2147483647", "2147483647") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("-2147483648", "-2147483648") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("+0", "-0") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("+0", "0") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("+0", "+0") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("-0", "-0") != 0  ) throw new RuntimeException(failMsg); 
        // -1
        if ( Base10Util.compareAsInt("0", "214748364") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("-0", "214748364") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("35000", "80000") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("79999", "80000") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("1589", "80000") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsInt("-2147483648", "2147483647") != -1  ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Errors(Suppose to throw).");
        // Empty string.
        try{
            Base10Util.compareAsInt("23", "");
            throw new RuntimeException(failMsg); 
        } catch(EmptyStringException e) {}

        try{
            Base10Util.compareAsInt("23", "");
            throw new RuntimeException(failMsg); 
        } catch(EmptyStringException e) {}

        // Incorrect format
        try{
            Base10Util.compareAsInt("23a", "748");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}
        
        try{
            Base10Util.compareAsInt("488887", "8742d");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}
        
        try{
            Base10Util.compareAsInt("-48888787", "-87442d");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}
        
        // Underflow/Overflow.
        try{
            Base10Util.compareAsInt("-2147483649", "-2147483648");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}

        try{
            Base10Util.compareAsInt("2147483647", "2147483648");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}
        System.out.println("TEST 2 Passed.");
        System.out.println();System.out.println();System.out.println();       
        end = System.currentTimeMillis();
        result = (end - start);
        
        System.out.println("=== Base10Util.compareAsInt(String firstNumber, String secondNumber) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void intOrSmaller(){
        long start, end, result;
        final String failMsg = "Base10Util.intOrSmaller() test fail.";
        System.out.println("=== Base10Util.intOrSmaller(String firstNumber, String secondNumber) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Case to case.");
        // Invalid to 
        if ( Base10Util.intOrSmaller("12345a", "-") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("123457a", "-2999999999") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("123459457b", "2999999999") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("124 87548", "") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller(" +124", "1234") != -1  ) throw new RuntimeException(failMsg); 
        // UnderFlow to
        if ( Base10Util.intOrSmaller("-21474836480", "#") != 1  ) throw new RuntimeException(failMsg);
        if ( Base10Util.intOrSmaller("-21474836488", "-214748364808") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("-21474836480", "214748364808") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("-21474836480", "") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("-21474836480", "122") != -1  ) throw new RuntimeException(failMsg);
        // OverFlow to 
        if ( Base10Util.intOrSmaller("21474836488", "12458784556875421547845215487(") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("214748364889", "-2147483648812454784547") != 1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("2147483648899", "+21474836484748741124788") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("21474836488124", "") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("+214748364888871", "0") != -1  ) throw new RuntimeException(failMsg); 
        // Empty to
        if ( Base10Util.intOrSmaller("", "888888888888333333333$33") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("", "-124875478752458787845") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("", "+12448787845487875458785") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("", "") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("", "12") != -1  ) throw new RuntimeException(failMsg);
        // Valid to
        if ( Base10Util.intOrSmaller("55", "44%") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("0", "-8887875487875487878") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("2487", "+8787875454878745487875") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("397857", "") != 1  ) throw new RuntimeException(failMsg); 
        
        if ( Base10Util.intOrSmaller("24", "35") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("85", "85") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.intOrSmaller("98", "80") != 1  ) throw new RuntimeException(failMsg); 
        System.out.println("TEST 1 Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.intOrSmaller(String firstNumber, String secondNumber) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void compareAsLong(){
        long start, end, result;
        final String failMsg = "Base10Util.compareAsLong() test fail.";
        System.out.println("=== Base10Util.compareAsLong(String firstNumber, String secondNumber) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Right format comparision.");
        // 1
        if ( Base10Util.compareAsLong("92233720368", "0") != 1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("92233720368", "-0") != 1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("80000000000", "350") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("80000000000", "79999999999") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("80000000000", "15895875") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("9223372036854775807", "-9223372036854775808") != 1  ) throw new RuntimeException(failMsg);
        // Equal.
        if ( Base10Util.compareAsLong("23", "+23") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("-23", "-23") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("9223372036854775807", "9223372036854775807") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("-9223372036854775808", "-9223372036854775808") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("+0", "-0") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("+0", "0") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("+0", "+0") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("-0", "-0") != 0  ) throw new RuntimeException(failMsg); 
        // -1
        if ( Base10Util.compareAsLong("0", "92233720368") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("-0", "92233720368") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("3500000000", "80000000000") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("79999999999", "80000000000") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("15895875", "80000000000") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.compareAsLong("-9223372036854775808", "9223372036854775807") != -1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Errors(Suppose to throw).");
        // Empty string.
        try{
            Base10Util.compareAsLong("23", "");
            throw new RuntimeException(failMsg); 
        } catch(EmptyStringException e) {}

        try{
            Base10Util.compareAsLong("23", "");
            throw new RuntimeException(failMsg); 
        } catch(EmptyStringException e) {}

        // Incorrect format.
        try{
            Base10Util.compareAsLong("23a", "748");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}
        
        try{
            Base10Util.compareAsLong("488887", "8742d");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}
        
        try{
            Base10Util.compareAsLong("-9223372036854775808", "-9223372036854775808d");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}
        
        // Underflow/Overflow
        try{
            Base10Util.compareAsLong("-9223372036854775808", "-9223372036854775809");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}

        try{
            Base10Util.compareAsLong("-9223372036854775809", "-9223372036854775808");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}

        try{
            Base10Util.compareAsLong("9223372036854775807", "9223372036854775808");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}

        try{
            Base10Util.compareAsLong("9223372036854775808", "9223372036854775807");
            throw new RuntimeException(failMsg); 
        } catch(NumberFormatException e) {}
        System.out.println("TEST 2 Passed.");
        System.out.println();System.out.println();System.out.println();       

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.compareAsLong(String firstNumber, String secondNumber) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void longOrSmaller(){
        long start, end, result;
        final String failMsg = "Base10Util.longOrSmaller() test fail.";
        System.out.println("=== Base10Util.longOrSmaller(String firstNumber, String secondNumber) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Case to case.");
        // Invalid to 
        if ( Base10Util.longOrSmaller("12345a", "-") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("123457a", "-922337203685477580888") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("123459457b", "9223372036854775808") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("124 87548", "") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller(" +124", "-9223372036854775808") != -1  ) throw new RuntimeException(failMsg); 
        // UnderFlow to
        if ( Base10Util.longOrSmaller("-92233720368547758088", "--") != 1  ) throw new RuntimeException(failMsg);
        if ( Base10Util.longOrSmaller("-92233720368547758088", "-9223372036854775808888888") != 0 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("-92233720368547758088", "92233720368547758088") != -1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("-92233720368547758088", "") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("-92233720368547758088", "-9223372036854775") != -1  ) throw new RuntimeException(failMsg);
        // OverFlow to 
        if ( Base10Util.longOrSmaller("9223372036854775808", "9223372036854775808548774587874(") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("9223372036854775808", "-9223372036854775808888") != 1 ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("9223372036854775808", "+92233720368547758088888888") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("9223372036854775808", "") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("+922337203685477580888", "0") != -1  ) throw new RuntimeException(failMsg); 
        // Empty to
        if ( Base10Util.longOrSmaller("", "92233720368547758081111d78") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("", "-92233720368547758085458") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("", "+922337203685477580887471254") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("", "") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("", "92233720368") != -1  ) throw new RuntimeException(failMsg);
        // Valid to
        if ( Base10Util.longOrSmaller("9223372035808", "92233720368547%75808") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("0", "-922337203685477580887") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("9223372036854", "+887878778787787875454878745487875") != 1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("922337203685477", "") != 1  ) throw new RuntimeException(failMsg); 
        
        if ( Base10Util.longOrSmaller("25", "35") != -1  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("85", "85") != 0  ) throw new RuntimeException(failMsg); 
        if ( Base10Util.longOrSmaller("98", "80") != 1  ) throw new RuntimeException(failMsg); 
        System.out.println("TEST 1 Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.longOrSmaller(String firstNumber, String secondNumber) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toIntAsUnsigned(){
        long start, end, result;
        final String failMsg = "Base10Util.toIntAsUnsigned() test fail.";
        System.out.println("=== Base10Util.toIntAsUnsigned(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();        
        System.out.println("TEST 1: Parse all case from 0 to 4294967295.");
        for ( long i = 0L; i < 4294967296L; i++ ){
            String number = ""+i;
            if ( !IntUtil.toStringAsUnsigned(Base10Util.toIntAsUnsigned(number)).equals(number) ){
                System.out.println("Wrong " + number);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 1 Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parsing with leading zeroes.");
        // 4294967295 unsigned or -1 signed with leading zeroes.
        if( Base10Util.toIntAsUnsigned("000000000000000004294967295") != -1 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if( Base10Util.toIntAsUnsigned("0000000002147483647") != 2147483647 ) throw new RuntimeException(failMsg);
        // 150000 with leading zeroes.
        if( Base10Util.toIntAsUnsigned("000000150000") != 150000 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if( Base10Util.toIntAsUnsigned("0000") != 0 ) throw new RuntimeException(failMsg);
        if( Base10Util.toIntAsUnsigned("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing fail cases.");
        // 1 too many.
        try{
            Base10Util.toIntAsUnsigned("4294967296");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{
            Base10Util.toIntAsUnsigned("42949672950");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.        
        try{
            Base10Util.toIntAsUnsigned("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toIntAsUnsigned("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toIntAsUnsigned("12314:");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toIntAsUnsigned("12345a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs where unsigned.
        try{
            Base10Util.toIntAsUnsigned("-1");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toIntAsUnsigned("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            Base10Util.toIntAsUnsigned("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}        
        System.out.println("TEST 3 Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.toIntAsUnsigned(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void toLongAsUnsigned(){
        long start, end, result;
        final String failMsg = "Base10Util.toLongAsUnsigned() test fail.";
        System.out.println("=== Base10Util.toLongAsUnsigned(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();        
        System.out.println("TEST 1: Parsing 10 millions number from 9223372036854775807, 10 millions below 18446744073709551615, 10 million from zero, and 10 million below 9223372036854775807.");
        for ( long i = 0L; i < 10000000L; i++){
            long number = Long.MAX_VALUE + i;
            if ( Base10Util.toLongAsUnsigned(LongUtil.toStringAsUnsigned(number)) != number  ) {
                System.out.println("Wrong " + number);
                throw new RuntimeException(failMsg);
            }
        }
        
        for ( long i = 0L; i < 10000000L; i++){
            long number = Long.MIN_VALUE - i;
            if ( Base10Util.toLongAsUnsigned(LongUtil.toStringAsUnsigned(number)) != number  ) {
                System.out.println("Wrong " + number);
                throw new RuntimeException(failMsg);
            }
        }
    
        for ( long i = 0L; i < 10000000L; i++){
            if ( Base10Util.toLongAsUnsigned(""+i) != i  ){
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }
    
        for ( long i = Long.MAX_VALUE; i > Long.MAX_VALUE - 10000001L ; i--){
            if ( Base10Util.toLongAsUnsigned(""+i) != i  ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 1 Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parsing with leading zeroes.");
        // 18446744073709551615 unsigned or -1L signed with leading zeroes.
        if( Base10Util.toLongAsUnsigned("0000000000000000018446744073709551615") != -1L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( Base10Util.toLongAsUnsigned("0000000000009223372036854775807") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // 1500000000000L with leading zeroes.
        if( Base10Util.toLongAsUnsigned("0000001500000000000") != 1500000000000L ) throw new RuntimeException(failMsg);
        // Zeroes.
        if( Base10Util.toLongAsUnsigned("0000") != 0L ) throw new RuntimeException(failMsg);
        if( Base10Util.toLongAsUnsigned("0") != 0L ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing fail cases.");
        // 1 too many.
        try{
            Base10Util.toLongAsUnsigned("18446744073709551616");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        // 1 digit too many.
        try{
            Base10Util.toLongAsUnsigned("184467440737095516150");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        // Incorrect digit.
        try{
            Base10Util.toLongAsUnsigned("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongAsUnsigned("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongAsUnsigned("12345:");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongAsUnsigned("12345a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signs where unsigned.
        try{
            Base10Util.toLongAsUnsigned("-1");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongAsUnsigned("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            Base10Util.toLongAsUnsigned("+0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        // Empty string.
        try{
            Base10Util.toLongAsUnsigned("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}        
        System.out.println("TEST 3 Passed.");
        System.out.println();System.out.println();System.out.println();
        
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== Base10Util.toLongAsUnsigned(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
}
