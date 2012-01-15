package hadoop.sample.hdfs;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EarthquakeCountMapperTest {

	private Mapper<LongWritable, Text, Text, DoubleWritable> mapper;

	private MapDriver<LongWritable, Text, Text, DoubleWritable> driver;

	@Before
	public void setUp() throws Exception {
		this.mapper = new EarthquakeCountMapper();
		this.driver = new MapDriver<LongWritable, Text, Text, DoubleWritable>(
				this.mapper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		this.driver.withInput(new LongWritable(1), new Text(
				"2011-04-19,23:20,23:10,茨城県南部,5,4"));

		this.driver.withOutput(new Text("2011-04-19"), new DoubleWritable(5.0));

		this.driver.runTest();
	}

}
