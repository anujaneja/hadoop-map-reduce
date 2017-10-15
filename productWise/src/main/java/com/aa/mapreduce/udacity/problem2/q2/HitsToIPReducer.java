package com.aa.mapreduce.udacity.problem2.q2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by anuj on 9/24/15.
 */
public class HitsToIPReducer extends Reducer<Text, IntWritable, Text, IntWritable> {


    @Override
    public void reduce(final Text key, final Iterable<IntWritable> values,
                       final Context context) throws IOException, InterruptedException {


        Iterator<IntWritable> iterator= values.iterator();

        int sum=0;

        while(iterator.hasNext()){
            sum+=iterator.next().get();
        }

        context.write(key, new IntWritable(sum));


    }
}
