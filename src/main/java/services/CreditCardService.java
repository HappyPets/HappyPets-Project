
package services;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	// Other methods -------------------------------------

	public boolean isValidCreditCard(final CreditCard creditCard) {
		boolean res = false;
		final boolean date = this.checkExpirationDate(creditCard);
		final boolean number = this.isValidCreditCardNumber(creditCard);
		if (date == false && number == true)
			res = true;
		return res;
	}

	public boolean isValidCreditCard2(final CreditCard creditCard) {
		boolean res = false;
		final boolean date = this.checkExpirationDate2(creditCard);
		final boolean number = this.isValidCreditCardNumber(creditCard);
		if (date == false && number == true)
			res = true;
		return res;
	}

	public boolean checkExpirationDate(final CreditCard creditCard) {
		boolean creditCardExpirated = false;
		final int actualMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int actualYear = Calendar.getInstance().get(Calendar.YEAR);
		if (actualYear > creditCard.getExpirationYear())
			creditCardExpirated = true;
		else if (actualYear == creditCard.getExpirationYear() && actualMonth > creditCard.getExpirationMonth())
			creditCardExpirated = true;
		return creditCardExpirated;
	}

	public boolean checkExpirationDate2(final CreditCard creditCard) {
		boolean creditCardExpirated = false;
		final int actualMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		final int actualYear = Calendar.getInstance().get(Calendar.YEAR);
		if (actualYear > creditCard.getExpirationYear())
			creditCardExpirated = true;
		else if (actualYear == creditCard.getExpirationYear() && actualMonth >= creditCard.getExpirationMonth())
			creditCardExpirated = true;
		return creditCardExpirated;
	}

	public boolean isValidCreditCardNumber(final CreditCard creditCard) {
		final String str = creditCard.getNumber();
		boolean res = false;
		final int[] ints = new int[str.length()];
		for (int i = 0; i < str.length(); i++)
			ints[i] = Integer.parseInt(str.substring(i, i + 1));
		for (int i = ints.length - 2; i >= 0; i = i - 2) {
			int j = ints[i];
			j = j * 2;
			if (j > 9)
				j = j % 10 + 1;
			ints[i] = j;
		}
		int sum = 0;
		for (int i = 0; i < ints.length; i++)
			sum += ints[i];
		if (sum % 10 == 0)
			res = true;
		return res;
	}
}
