package com.aa.mapreduce.udacity.problem2.q1;

import com.util.ApacheAccessLogParser;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by anuj on 9/24/15.
 */
public class HitsToPageMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text pageName= new Text();
    private final IntWritable ONE= new IntWritable(1);

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();


        ApacheAccessLogParser apacheAccessLogParser=null;

        try{
            apacheAccessLogParser= ApacheAccessLogParser.parseFromLogLine(line);

            pageName.set(apacheAccessLogParser.getEndpoint());

            if(apacheAccessLogParser.getEndpoint().contains("the-associates.js")){
                System.out.println("-----pageName----"+apacheAccessLogParser.getEndpoint());
            }

            context.write(pageName,ONE);

        } catch (Exception ex){
            System.out.println(ex.getMessage()+"Exception occurs in parsing log"+line);
            ex.printStackTrace();

            context.write(new Text("Exception"),ONE);



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
