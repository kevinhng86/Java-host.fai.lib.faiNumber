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
import host.fai.lib.faiNumber.IntUtil;
import java.util.Random;
public final class IntUtilBenchmark{
    public static void main(String[] args){
        long start;
        long end;
        long result;
        int round = 10000000;
        int[] numbers = new int[round];
        Random r = new Random();

        System.out.println("=== faiNumber.IntUtil.toSignedBinary(), Integer.toBinaryString() Benchmark === ");
        System.out.println();
        System.out.println("== Generating random data.");
        System.out.println();
        for ( int i = 0; i < round; i++ ){
            numbers[i] = r.nextInt(Integer.MAX_VALUE);
        }
        System.out.println("== Random data generating done.");
        System.out.println(String.format("Printing one of the random data to make sure they are all right. '%d'.", numbers[0]));
        System.out.println();
        System.out.println();
        System.out.println();

        /** 10000000 different string benchmark */
        System.out.println("== faiNumber.IntUtil.toSignedBinary() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) IntUtil.toSignedBinary(numbers[i]);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("faiNumber.IntUtil.toSignedBinary() parsed '%d' number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("== Integer.toBinaryString() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Integer.toBinaryString(numbers[i]);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Integer.toBinaryString() parsed '%d' number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        /** Same string parsing benchmark **/
        System.out.println("== Starting Same String Parsing Benchmark Test");
        int number = Integer.MAX_VALUE;

        System.out.println("== faiNumber.IntUtil.toSignedBinary() Same String Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) IntUtil.toSignedBinary(number);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("faiNumber.IntUtil.toSignedBinary() parsed string '%s' '%d' times in %d ms.", number, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("== Integer.toBinaryString() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Integer.toBinaryString(number);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Integer.toBinaryString() parsed string '%s' '%d' times in %d ms.", number, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
