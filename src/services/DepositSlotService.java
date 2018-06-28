package services;

public class DepositSlotService {
	// indicates whether envelope was received (always returns true,
	// because this is only a software simulation of a real deposit slot)
	public boolean isEnvelopeReceived() {
		return true;
	}
}