package com.aa.mapreduce.udacity.problem1.q3;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by anuj on 9/24/15.
 */
public class TotalSalesReducer extends Reducer<Text, DoubleWritable, IntWritable, DoubleWritable> {


    @Override
    public void reduce(final Text key, final Iterable<DoubleWritable> values,
                       final Context context) throws IOException, InterruptedException {


        Iterator<DoubleWritable> iterator = values.iterator();

        Double sum=0.0;

        int i=0;
        while (iterator.hasNext()) {
            i++;
            sum+=iterator.next().get();
        }


        context.write(new IntWritable(i),new DoubleWritable(sum));

    }
}
