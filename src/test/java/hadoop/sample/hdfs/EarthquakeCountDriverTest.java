package hadoop.sample.hdfs;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class EarthquakeCountDriverTest {

	private Mapper<LongWritable, Text, Text, DoubleWritable> mapper;
	private Reducer<Text, DoubleWritable, Text, IntWritable> reducer;

	private MapReduceDriver<LongWritable, Text, Text, DoubleWritable, Text, IntWritable> driver;

	@Before
	public void setUp() {
		this.mapper = new EarthquakeCountMapper();
		this.reducer = new EarthquakeCountReducer();
		this.driver = new MapReduceDriver<LongWritable, Text, Text, DoubleWritable, Text, IntWritable>(
				this.mapper, this.reducer);
	}

	@Test
	public void test() {
		this.driver.addInput(new LongWritable(1), new Text(
				"2011-04-19,23:20,23:10,茨城県南部,5,4"));
		this.driver.addInput(new LongWritable(2), new Text(
				"2011-04-19,23:04,23:00,秋田県内陸南部,2.4,2"));
		this.driver.addInput(new LongWritable(3), new Text(
				"2011-04-20,22:11,22:06,奄美大島北西沖,4.1,1"));
		this.driver.addInput(new LongWritable(4), new Text(
				"2011-04-20,16:33,16:28,岡山県南部,2.8,1"));

		this.driver.withOutput(new Text("2011-04-19"), new IntWritable(2));
		this.driver.withOutput(new Text("2011-04-20"), new IntWritable(2));

		this.driver.runTest();
	}

}
