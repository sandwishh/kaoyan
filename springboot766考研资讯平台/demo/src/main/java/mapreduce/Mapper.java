package mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text month = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] parts = value.toString().split(" - ");
        if (parts.length < 3) {
            return;
        }

        String timestamp = parts[1].replace("[", "").replace("]", "");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(timestamp);
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            month.set(monthFormat.format(date));
            context.write(month, one);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
