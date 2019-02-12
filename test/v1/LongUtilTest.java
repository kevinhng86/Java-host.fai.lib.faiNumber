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
import host.fai.lib.faiNumber.LongUtil;
import host.fai.lib.faiNumber.Base10Util;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;
import java.util.Random;

/**
 * <p>The <code>LongUtilTest</code> is a test unit for 
 * {@code host.fai.lib.faiNumber.LongUtil}
 * 
 * @author  Khang Hoang Nguyen
 **/
final class LongUtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        LongUtilTestHelper.toSignedBinary();
        System.out.println(separator);
        LongUtilTestHelper.toSignedOctal();
        System.out.println(separator);
        LongUtilTestHelper.toSignedHex();
        System.out.println(separator);
        LongUtilTestHelper.toStringAsUnsigned();
        System.out.println(separator);
        LongUtilTestHelper.isOdd();
        System.out.println(separator);
        LongUtilTestHelper.isEven();
    }
}

final class LongUtilTestHelper{    
    public static void toSignedBinary(){
        long start, end, result;
        final String failMsg = "LongUtil.toSignedBinary() test fail.";
        System.out.println("=== LongUtil.toSignedBinary(long input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();    
        System.out.println("TEST 1: Parsing 400 million numbers.");
        for ( long i = Long.MIN_VALUE; i < Long.MIN_VALUE + 100000000L ; i++ ){
            if ( Long.parseLong(LongUtil.toSignedBinary(i),2) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done low range.");
        for ( long i = 0; i < 0 - 100000000L; i-- ){
            if ( Long.parseLong(LongUtil.toSignedBinary(i),2) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done low 0.");
        for ( long i = 0; i < 0 + 100000000L; i++ ){
            if ( Long.parseLong(LongUtil.toSignedBinary(i),2) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done high 0.");
        for ( long i = Long.MAX_VALUE; i != Long.MAX_VALUE - 100000000L; i-- ){
            if ( Long.parseLong(LongUtil.toSignedBinary(i),2) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done high range.");
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parsing 100 million random numbers.");

        Random r = new Random();
        long num; 
        for ( int i = 0; i < 100000000; i++ ){
            num = r.nextLong();
            if ( Long.parseLong(LongUtil.toSignedBinary(num),2) != num ) throw new RuntimeException(failMsg);            
        }
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();
            
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== LongUtil.toSignedBinary(long input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
    
    public static void toSignedOctal(){
        long start, end , result;            
        final String failMsg = "LongUtil.toSignedOctal() test fail.";
        System.out.println("=== LongUtil.toSignedOctal(long input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
       
        start = System.currentTimeMillis();    
        System.out.println("TEST 1: Parsing 400 million numbers.");
        for ( long i = Long.MIN_VALUE; i < Long.MIN_VALUE + 100000000L ; i++ ){
            if ( Long.parseLong(LongUtil.toSignedOctal(i),8) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done low range.");
        for ( long i = 0; i < 0 - 100000000L; i-- ){
            if ( Long.parseLong(LongUtil.toSignedOctal(i),8) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done low 0.");
        for ( long i = 0; i < 0 + 100000000L; i++ ){
            if ( Long.parseLong(LongUtil.toSignedOctal(i),8) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done high 0.");
        for ( long i = Long.MAX_VALUE; i != Long.MAX_VALUE - 100000000L; i-- ){
            if ( Long.parseLong(LongUtil.toSignedOctal(i),8) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done high range.");
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parsing 100 million random numbers.");

        Random r = new Random();
        long num; 
        for ( int i = 0; i < 100000000; i++ ){
            num = r.nextLong();
            if ( Long.parseLong(LongUtil.toSignedOctal(num),8) != num ) throw new RuntimeException(failMsg);            
        }
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();
       
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== LongUtil.toSignedOctal(long input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
    
    public static void toSignedHex(){
        long start, end, result;
        final String failMsg = "LongUtil.toSignedHex() test fail.";
        System.out.println("=== LongUtil.toSignedHex(long input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();    
        System.out.println("TEST 1: Parsing 400 million numbers.");
        for ( long i = Long.MIN_VALUE; i < Long.MIN_VALUE + 100000000L ; i++ ){
            if ( Long.parseLong(LongUtil.toSignedHex(i),16) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done low range.");
        for ( long i = 0; i < 0 - 100000000L; i-- ){
            if ( Long.parseLong(LongUtil.toSignedHex(i),16) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done low 0.");
        for ( long i = 0; i < 0 + 100000000L; i++ ){
            if ( Long.parseLong(LongUtil.toSignedHex(i),16) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done high 0.");
        for ( long i = Long.MAX_VALUE; i != Long.MAX_VALUE - 100000000L; i-- ){
            if ( Long.parseLong(LongUtil.toSignedHex(i),16) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done high range.");
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parsing 100 million random numbers.");

        Random r = new Random();
        long num; 
        for ( int i = 0; i < 100000000; i++ ){
            num = r.nextLong();
            if ( Long.parseLong(LongUtil.toSignedHex(num),16) != num ) throw new RuntimeException(failMsg);            
        }
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();
       
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== LongUtil.toSignedHex(long input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }

    public static void toStringAsUnsigned(){
        long start, end, result;
        final String failMsg = "LongUtil.toStringAsUnsigned() test fail.";
        System.out.println("=== LongUtil.toStringAsUnsigned(long input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();    
        System.out.println("TEST 1: Parsing 400 million numbers.");
        for ( long i = Long.MIN_VALUE; i < Long.MIN_VALUE + 100000000L ; i++ ){
            if ( Base10Util.toLongAsUnsigned(LongUtil.toStringAsUnsigned(i)) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done low range.");
        for ( long i = 0; i < 0 - 100000000L; i-- ){
            if ( Base10Util.toLongAsUnsigned(LongUtil.toStringAsUnsigned(i)) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done low 0.");
        for ( long i = 0; i < 0 + 100000000L; i++ ){
            if ( Base10Util.toLongAsUnsigned(LongUtil.toStringAsUnsigned(i)) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done high 0.");
        for ( long i = Long.MAX_VALUE; i != Long.MAX_VALUE - 100000000L; i-- ){
            if ( Base10Util.toLongAsUnsigned(LongUtil.toStringAsUnsigned(i)) != i ) throw new RuntimeException(failMsg);            
        }
        System.out.println("Done high range.");
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parsing 100 million random numbers.");

        Random r = new Random();
        long num; 
        for ( int i = 0; i < 100000000; i++ ){
            num = r.nextLong();
            if ( Base10Util.toLongAsUnsigned(LongUtil.toStringAsUnsigned(num)) != num ) throw new RuntimeException(failMsg);            
        }
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();
    
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== LongUtil.toStringAsUnsigned(long input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }

    public static void isOdd(){
        long start, end, result;
        final String failMsg = "LongUtil.isOdd() test fail.";
        System.out.println("=== LongUtil.isOdd(long input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Testing 5 numbers.");
    
        if ( LongUtil.isOdd(-255555555555555555L) != true ) throw new RuntimeException(failMsg); 
        if ( LongUtil.isOdd(-877777777777777774L) != false ) throw new RuntimeException(failMsg);
        if ( LongUtil.isOdd(874111111111111111L) != true ) throw new RuntimeException(failMsg);
        if ( LongUtil.isOdd(897777777777777770L) != false ) throw new RuntimeException(failMsg);
        if ( LongUtil.isOdd(0L) != false) throw new RuntimeException(failMsg);
        
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
    
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== LongUtil.isOdd(long input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
    
    public static void isEven(){
        long start, end, result;
        final String failMsg = "LongUtil.isEven() test fail.";
        System.out.println("=== LongUtil.isEven(long input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Testing 5 numbers.");
    
        if ( LongUtil.isEven(-8878888888888880L) != true ) throw new RuntimeException(failMsg); 
        if ( LongUtil.isEven(-8880000000000071L) != false ) throw new RuntimeException(failMsg);
        if ( LongUtil.isEven(850000000004078L) != true ) throw new RuntimeException(failMsg);
        if ( LongUtil.isEven(999899999999979L) != false ) throw new RuntimeException(failMsg);
        if ( LongUtil.isEven(0L) != true ) throw new RuntimeException(failMsg);
        
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
    
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== LongUtil.isEven(long input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
}
