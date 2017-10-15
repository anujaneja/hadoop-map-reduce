package com.aa.mapreduce.udacity.problem1.q2;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by anuj on 9/24/15.
 */
public class HighestSalePerStoreReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {


    @Override
    public void reduce(final Text key, final Iterable<DoubleWritable> values,
                       final Context context) throws IOException, InterruptedException {

        Double highestSale = 0.0;
        Double currentSale=0.0;

        Iterator<DoubleWritable> iterator = values.iterator();

        while (iterator.hasNext()) {
            currentSale=iterator.next().get();

            if(currentSale>highestSale){
                highestSale=currentSale;
            }
        }


        context.write(key, new DoubleWritable(highestSale));

    }
}
