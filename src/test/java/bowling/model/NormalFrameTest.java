package bowling.model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exception.InvalidPinNumberException;

public class NormalFrameTest {

	NormalFrame frame;
	
	@Before
	public void setup() {
		frame = new NormalFrame();
		frame.play(2);
	}
	
	@Test
	public void 투구1() {
		//play 한 번 돌았기 때문에 tryNo != 1 이라 그 부분을 안 탐
		assertThat(frame.getStatus()).isEqualTo("2");
	}

	@Test
	public void 미스() {
		frame.play(2);
		assertThat(frame.getStatus()).isEqualTo("2|2");
	}
	@Test
	public void 스페어() {
		frame.play(8);
		assertThat(frame.getStatus()).isEqualTo("2|/");
	}
	@Test
	public void 스트라이크() {
		frame = new NormalFrame();
		frame.play(10);
		assertThat(frame.getStatus()).isEqualTo("X");
	}
	@Test
	public void 제로() {
		frame.play(0);
		assertThat(frame.getStatus()).isEqualTo("2|-");
	}
	
	@Test(expected = InvalidPinNumberException.class)
	public void 익셉션() {
		frame.play(9);
	}
	@Test
	public void 미스_합계() {
		frame = new NormalFrame();
		frame.play(1);
		frame.play(2);
		assertThat(frame.getSum()).isEqualTo(3);
	}
	@Test
	public void 스페어_합계() {
		Frame frame1 = new NormalFrame();
		Frame frame2 = new NormalFrame();
		frame1.play(3);
		frame1.play(7);
		frame2.play(7, frame1);
		assertThat(frame1.getSum()).isEqualTo(17);
	}
	@Test
	public void 스트라이크_미스_합계() {
		Frame frame1 = new NormalFrame();
		Frame frame2 = new NormalFrame();
		frame1.play(10);
		frame2.play(2, frame1);
		frame2.play(3, frame1);
		assertThat(frame1.getSum()).isEqualTo(15);
	}
	@Test
	public void 스트라이크_스페어_합계() {
		Frame frame1 = new NormalFrame();
		Frame frame2 = new NormalFrame();
		frame1.play(10);
		frame2.play(4, frame1);
		frame2.play(6, frame1);
		assertThat(frame1.getSum()).isEqualTo(20);
	}
}
