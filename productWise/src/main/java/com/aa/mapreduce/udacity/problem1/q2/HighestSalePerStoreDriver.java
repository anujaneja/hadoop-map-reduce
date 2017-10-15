package com.aa.mapreduce.udacity.problem1.q2;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by anuj on 9/24/15.
 */
public class HighestSalePerStoreDriver extends Configured implements Tool {


    public int run(String[] args) throws Exception {

        System.out.println("Length:::"+args.length +"args:::"+args[0]+args[1]);

        if (args.length != 2) {
            System.out.println("Usage: [input] [output]");
            System.exit(-1);
        }

        Job job = Job.getInstance(getConf());
        job.setJobName("HighestSalePerStore");
        job.setJarByClass(HighestSalePerStoreDriver.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        job.setMapperClass(HighestSalePerStoreMapper.class);
        job.setCombinerClass(HighestSalePerStoreReducer.class);
        job.setReducerClass(HighestSalePerStoreReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        Path inputFilePath = new Path(args[0]);
        Path outputFilePath = new Path(args[1]);

		/* This line is to accept the input recursively */
        FileInputFormat.setInputDirRecursive(job, true);

        FileInputFormat.addInputPath(job, inputFilePath);
        FileOutputFormat.setOutputPath(job, outputFilePath);

		/*
		 * Delete output filepath if already exists
		 */
        FileSystem fs = FileSystem.newInstance(getConf());

        if (fs.exists(outputFilePath)) {
            fs.delete(outputFilePath, true);
        }

        return job.waitForCompletion(true) ? 0: 1;
    }

    public static void main(String[] args) throws Exception {
        HighestSalePerStoreDriver productWiseDriver = new HighestSalePerStoreDriver();
        int res = ToolRunner.run(productWiseDriver, args);
        System.exit(res);
    }
}
