package mocks;

import ui.Input;

public class KeypadMock implements Input {
	
	private Integer value;
	
	@Override
	public int getInput() {
		return value;
	}
	
	public void setInput(Integer value) {
		this.value = value;
	}

}
