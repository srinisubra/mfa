package edu.gatech.mfa.extn;

import edu.gatech.mfa.extn.otpemail.exception.InvalidNumberOfFactorException;

public class TwoFactorUserCredential extends UserCredential{

	private String firstFactor;
	private String secondFactor;
	
	@Override
	public Object getFactor(int factorNumber) throws InvalidNumberOfFactorException {
		if (factorNumber < 0 && factorNumber > 1)
			throw new InvalidNumberOfFactorException(
					"Supported Factors [2] found [" + factorNumber
							+ "] in request");

		if (factorNumber == 0)
			return firstFactor;
		else
			return secondFactor;
	}

	@Override
	public void setFactor(int factorNumber, Object factor) throws InvalidNumberOfFactorException {
		if (factorNumber < 0 && factorNumber > 1)
			throw new InvalidNumberOfFactorException(
					"Supported Factors [2] found [" + factorNumber
							+ "] in request");

		if (factorNumber == 0) {
			firstFactor = factor.toString();
		} else {
			secondFactor = factor.toString();
		}
		
	}

}
