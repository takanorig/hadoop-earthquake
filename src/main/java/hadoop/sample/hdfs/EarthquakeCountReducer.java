package hadoop.sample.hdfs;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EarthquakeCountReducer extends
		Reducer<Text, DoubleWritable, Text, IntWritable> {

	public EarthquakeCountReducer() {
	}

	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values,
			Context context) throws IOException, InterruptedException {

		IntWritable result = new IntWritable();

		int count = 0;
		for (DoubleWritable tempValue : values) {
			count++;
		}
		result.set(count);
		
		context.write(key, result);
	}

}
