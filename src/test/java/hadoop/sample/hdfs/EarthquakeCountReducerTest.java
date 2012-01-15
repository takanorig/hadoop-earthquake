package hadoop.sample.hdfs;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EarthquakeCountReducerTest {

	private Reducer<Text, DoubleWritable, Text, IntWritable> reducer;

	private ReduceDriver<Text, DoubleWritable, Text, IntWritable> driver;

	@Before
	public void setUp() throws Exception {
		this.reducer = new EarthquakeCountReducer();
		this.driver = new ReduceDriver<Text, DoubleWritable, Text, IntWritable>(
				this.reducer);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<DoubleWritable> values = new ArrayList<DoubleWritable>();
		values.add(new DoubleWritable(5.0));
		values.add(new DoubleWritable(2.4));
		values.add(new DoubleWritable(4.1));

		this.driver.withInput(new Text("2011-04-19"), values);

		this.driver.withOutput(new Text("2011-04-19"), new IntWritable(3));

		this.driver.runTest();
	}

}
