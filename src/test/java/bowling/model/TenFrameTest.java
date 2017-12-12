package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import exception.InvalidPinNumberException;

public class TenFrameTest {

	TenFrame frame;
	
	@Before
	public void setup() {
		frame = new TenFrame();
	}
	@Test
	public void 투구_전() {
		assertThat(frame.getStatus()).isEqualTo("");
		assertThat(frame.getScore()).isEqualTo(-1);
	}

	@Test
	public void X() {
		frame.play(10);
		assertThat(frame.getStatus()).isEqualTo("X");
	}
	
	@Test
	public void XX() {
		frame.play(10).play(10);
		assertThat(frame.getStatus()).isEqualTo("X|X");
	}
	@Test
	public void XXX() {
		frame.play(10).play(10).play(10);
		assertThat(frame.getStatus()).isEqualTo("X|X|X");
	}
	@Test
	public void X_스페어() {
		frame.play(10);
		frame.play(3);
		frame.play(7);
		assertThat(frame.getStatus()).isEqualTo("X|3|/");
	}
	@Test
	public void X_미스() {
		frame.play(10);
		frame.play(5);
		frame.play(4);
		assertThat(frame.getStatus()).isEqualTo("X|5|4");
	}
	@Test
	public void 스페어_X() {
		frame.play(2);
		frame.play(8);
		frame.play(10);
		assertThat(frame.getStatus()).isEqualTo("2|/|X");
	}
	@Test
	public void 스페어_미스() {
		frame.play(4);
		frame.play(6);
		frame.play(5);
		assertThat(frame.getStatus()).isEqualTo("4|/|5");
	}
	@Test
	public void 미스() {
		frame.play(2);
		frame.play(0);
		assertThat(frame.getStatus()).isEqualTo("2|-");
	}
	@Test
	public void 스페어() {
		frame.play(2);
		frame.play(8);
		assertThat(frame.getStatus()).isEqualTo("2|/");
	}

	@Test
	public void 미스_끝내기() {
		frame.play(2);
		frame.play(3);
		assertThat(frame.isEnd()).isEqualTo(true);
	}
	@Test
	public void 스페어_끝내기() {
		frame.play(2);
		frame.play(8);
		assertThat(frame.isEnd()).isEqualTo(false);
		frame.play(8);
		assertThat(frame.isEnd()).isEqualTo(true);
	}
	@Test
	public void X_끝내기() {
		frame.play(10);
		assertThat(frame.isEnd()).isEqualTo(false);
		frame.play(2);
		assertThat(frame.isEnd()).isEqualTo(false);
		frame.play(8);
		assertThat(frame.isEnd()).isEqualTo(true);
	}
	
	@Test(expected = InvalidPinNumberException.class)
	public void 투구2_익셉션() {
		frame.play(6);
		frame.play(6);
	}
	
	@Test
	public void 투구2_익셉션_안남() {
		frame.play(10);
		frame.play(6);
		assertThat(frame.getStatus()).isEqualTo("X|6");
	}
	
	@Test(expected = InvalidPinNumberException.class)
	public void 투구3_익셉션() {
		frame.play(10);
		frame.play(6);
		frame.play(6);
	}
	
	@Test
	public void 투구3_익셉션_안남() {
		frame.play(10);
		frame.play(6);
		frame.play(4);
		assertThat(frame.getStatus()).isEqualTo("X|6|/");
	}
	
	@Test
	public void 미스_합계() {
		frame = new TenFrame();
		frame.play(1);
		frame.play(2);
		assertThat(frame.getScore()).isEqualTo(3);
	}
	@Test
	public void 스페어_합계() {
		Frame frame2 = new TenFrame();
		frame2.play(3);
		frame2.play(7);
		frame2.play(5);
		assertThat(frame2.getScore()).isEqualTo(15);
	}
	@Test
	public void X_미스_합계() {
		Frame frame2 = new TenFrame();
		frame2.play(10);
		frame2.play(2);
		frame2.play(3);
		assertThat(frame2.getScore()).isEqualTo(15);
	}
	@Test
	public void X_스페어_합계() {
		Frame frame2 = new TenFrame();
		frame2.play(10);
		frame2.play(4);
		frame2.play(6);
		assertThat(frame2.getScore()).isEqualTo(20);
	}
	@Test
	public void XX_합계() {
		Frame frame2 = new TenFrame();
		frame2.play(10);
		frame2.play(10);
		frame2.play(2);
		assertThat(frame2.getScore()).isEqualTo(22);
	}
	@Test
	public void XXX_합계() {
		Frame frame2 = new TenFrame();
		frame2.play(10);
		frame2.play(10);
		frame2.play(10);
		assertThat(frame2.getScore()).isEqualTo(30);
	}
	@Test
	public void 전전프레임_전프레임_프레임_합계() {
		Frame frame1 = new NormalFrame(8);
		Frame frame2 = frame1.play(10);
		Frame frame3 = frame2.play(10);
		frame3.play(10);
		frame3.play(1);
		frame3.play(1);
		assertThat(frame1.getScore()).isEqualTo(30);
		assertThat(frame2.getScore()).isEqualTo(21);
		assertThat(frame3.getScore()).isEqualTo(12);
	}
}
