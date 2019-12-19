package my_decision_tree;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomFish {
	String name;
	double size;
	String color;
	boolean verticalBars;
	boolean teeth;
	boolean spotted;
	int maxAge;
	Random rn = new Random();
	int nameNum = rn.nextInt(12 - 1 + 1) + 1;

	double sizeNum = Math.random();
	double colorNum = Math.random();

	DecimalFormat df = new DecimalFormat("#.00");

	public RandomFish() {
		if (nameNum == 1) {
			name = "Largemouth Bass";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (15.7 - 11.8) + 11.8;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (26.95 - 15.71) + 15.71;
			else
				size = rn.nextDouble() * (38.2 - 26.96) + 27.96;

			if (colorNum < .50)
				color = "green";
			else
				color = "brown";

			verticalBars = false;
			teeth = false;
			spotted = false;
			maxAge = 23;
		} else if (nameNum == 2) {
			name = "Smallmouth Bass";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (16 - 12) + 12;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (21.6 - 16.01) + 16.01;
			else
				size = rn.nextDouble() * (27.2 - 21.61) + 21.61;

			if (colorNum < .80)
				color = "brown";
			else if (colorNum >= .80 && colorNum < .90)
				color = "green";
			else
				color = "black";

			verticalBars = true;
			teeth = false;
			spotted = false;
			maxAge = 26;
		} else if (nameNum == 3) {
			name = "Brown Bullhead";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (16 - 7.9) + 7.9;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (17.85 - 16.01) + 16.01;
			else
				size = rn.nextDouble() * (19.7 - 17.86) + 17.86;

			color = "brown";
			verticalBars = false;
			teeth = false;
			spotted = true;
			maxAge = 9;
		} else if (nameNum == 4) {
			name = "Black Crappie";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (8 - 4) + 4;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (13.65 - 8.01) + 8.01;
			else
				size = rn.nextDouble() * (19.3 - 13.66) + 13.66;

			if (colorNum < .50)
				color = "grey";
			else
				color = "green";

			verticalBars = false;
			teeth = true;
			spotted = true;
			maxAge = 15;
		} else if (nameNum == 5) {
			name = "Lake Sturgeon";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (47.2 - 35.4) + 35.4;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (71.6 - 47.21) + 47.21;
			else
				size = rn.nextDouble() * (96 - 71.61) + 71.61;

			if (colorNum < .50)
				color = "brown";
			else
				color = "grey";

			verticalBars = false;
			teeth = false;
			spotted = false;
			maxAge = 152;
		} else if (nameNum == 6) {
			name = "Muskellunge";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (48 - 28) + 28;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (60 - 48.01) + 48.01;
			else
				size = rn.nextDouble() * (72 - 60.01) + 60.01;

			if (colorNum < .33)
				color = "brown";
			else if (colorNum >= .33 && colorNum < .66)
				color = "grey";
			else
				color = "green";

			verticalBars = true;
			teeth = true;
			spotted = true;
			maxAge = 30;
		} else if (nameNum == 7) {
			name = "Northern Pike";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (22 - 16) + 16;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (40.5 - 22.01) + 22.01;
			else
				size = rn.nextDouble() * (59 - 40.51) + 40.51;

			color = "green";
			verticalBars = false;
			teeth = true;
			spotted = true;
			maxAge = 30;
		} else if (nameNum == 8) {
			name = "Bluegill";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (7.5 - 4) + 4;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (11.75 - 7.51) + 7.51;
			else
				size = rn.nextDouble() * (16 - 11.76) + 11.76;

			color = "green";
			verticalBars = true;
			teeth = true;
			spotted = false;
			maxAge = 10;
		}

		else if (nameNum == 9) {
			name = "Walleye";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (21.2 - 14.2) + 14.2;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (31.6 - 21.21) + 21.21;
			else
				size = rn.nextDouble() * (42 - 31.61) + 31.61;

			color = "yellow";
			verticalBars = false;
			teeth = true;
			spotted = true;
			maxAge = 29;
		} else if (nameNum == 10) {
			name = "Yellow Perch";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (11.4 - 3.9) + 3.9;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (14.7 - 11.41) + 11.41;
			else
				size = rn.nextDouble() * (18 - 14.71) + 14.71;

			color = "yellow";
			verticalBars = true;
			teeth = false;
			spotted = false;
			maxAge = 11;
		} else if (nameNum == 11) {
			name = "Rock Bass";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (10 - 6) + 6;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (13.45 - 10.01) + 10.01;
			else
				size = rn.nextDouble() * (16.9 - 13.46) + 13.46;

			if (colorNum < .50)
				color = "brown";
			else
				color = "green";

			verticalBars = false;
			teeth = true;
			spotted = false;
			maxAge = 18;
		} else if (nameNum == 12) {
			name = "Channel Catfish";

			if (sizeNum < .84135)
				size = rn.nextDouble() * (26.4 - 21.2) + 21.2;
			else if (sizeNum >= .84135 && sizeNum < .97725)
				size = rn.nextDouble() * (39.2 - 26.41) + 26.41;
			else
				size = rn.nextDouble() * (52 - 39.21) + 39.21;

			if (colorNum < .33)
				color = "green";
			else if (colorNum >= .33 && colorNum < .66)
				color = "grey";
			else
				color = "black";

			verticalBars = false;
			teeth = false;
			spotted = true;
			maxAge = 24;
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isVerticalBars() {
		return verticalBars;
	}

	public void setVerticalBars(boolean verticalBars) {
		this.verticalBars = verticalBars;
	}

	public boolean isTeeth() {
		return teeth;
	}

	public void setTeeth(boolean teeth) {
		this.teeth = teeth;
	}

	public boolean isSpotted() {
		return spotted;
	}

	public void setSpotted(boolean spotted) {
		this.spotted = spotted;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public void print() {

		System.out.println("Name:" + this.getName() + ",Size:" + df.format(this.getSize()) + ",Color:" + this.getColor()
				+ ",Teeth:" + this.isTeeth() + ",Vertical Bars:" + this.isVerticalBars() + ",Spotted:"
				+ this.isSpotted() + ",Max Age:" + this.getMaxAge());
		if (sizeNum >= .84135) {
			System.out.println("Rare size siting");
		}
	}

	public String[] createArray() {
		String[] arrayFormat = new String[7];
		arrayFormat[0] = String.valueOf(df.format(this.getSize()));
		arrayFormat[1] = color;
		if (this.isVerticalBars())
			arrayFormat[2] = "Vertical bars";
		else
			arrayFormat[2] = "No vertical bars";

		if (this.isTeeth())
			arrayFormat[3] = "Teeth";
		else
			arrayFormat[3] = "No teeth";

		if (this.isSpotted())
			arrayFormat[4] = "Spotted";
		else
			arrayFormat[4] = "Not spotted";

		arrayFormat[5] = String.valueOf(this.getMaxAge());
		arrayFormat[6] = name;

		return arrayFormat;
	}
}
