package com.aa.mapreduce.salesanalysis;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by anuj on 9/24/15.
 */
public class SalesAnalyserReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

    private DoubleWritable totalSalePerStore = new DoubleWritable();

    @Override
    public void reduce(final Text key, final Iterable<DoubleWritable> values,
                       final Context context) throws IOException, InterruptedException {

        Double sum = 0.0;

        Iterator<DoubleWritable> iterator = values.iterator();

        while (iterator.hasNext()) {
            sum += iterator.next().get();
        }

        totalSalePerStore.set(sum);
        context.write(key, totalSalePerStore);
    }
}
