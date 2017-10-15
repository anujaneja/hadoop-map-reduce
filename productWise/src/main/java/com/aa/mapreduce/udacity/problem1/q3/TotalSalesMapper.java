package com.aa.mapreduce.udacity.problem1.q3;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by anuj on 9/24/15.
 */
public class TotalSalesMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

    private Text totalSalesKey = new Text();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        StringTokenizer tokenizer = new StringTokenizer(line,"\t");

        ArrayList<String> tokens=new ArrayList<String>();

        while (tokenizer.hasMoreTokens()) {

            tokens.add(tokenizer.nextToken());
        }

        if(tokens.size()==6){
            totalSalesKey.set("TotalSales");
            DoubleWritable price=  new DoubleWritable(Double.parseDouble(tokens.get(4)));
            context.write(totalSalesKey,price);

        } else{
            System.out.println("Wrong format of row found hence: Skipping don't worry!!!"+tokens.toString());

        }



    }

    public void run(Context context) throws IOException, InterruptedException {
        setup(context);
        while (context.nextKeyValue()) {
            map(context.getCurrentKey(), context.getCurrentValue(), context);
        }
        cleanup(context);
    }




}
