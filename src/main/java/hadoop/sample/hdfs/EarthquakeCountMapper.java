package hadoop.sample.hdfs;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EarthquakeCountMapper extends
		Mapper<LongWritable, Text, Text, DoubleWritable> {

	private Text dateValue = new Text();
	private DoubleWritable magnitudeValue = new DoubleWritable();

	public EarthquakeCountMapper() {
	}

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();

		// コメントや空行はスキップする。
		if (line.startsWith("#") || line.trim().isEmpty()) {
			return;
		}

		// 1行のデータをトークンに分解する。
		String[] tokens = line.split(",");
		String dateStr = null;
		String magnitudeStr = null;
		if (tokens.length > 4) {
			dateStr = tokens[0];
			magnitudeStr = tokens[4];
		}

		// key-value の作成
		this.dateValue.set(dateStr);
		this.magnitudeValue.set(Double.parseDouble(magnitudeStr));

		context.write(dateValue, magnitudeValue);
	}
}
