package jportfolio.com.protect;

import java.util.List;

import jportfolio.com.model.Asset;
import jportfolio.com.model.Portfolio;

/**
 * PPS means Portfolio Protection Strategies
 * 
 * 
 * @author wendellopes
 *
 */

public class PPS {

	/**
	 * 
	 * Diversification
	 * 
	 * One of the cornerstones of Modern Portfolio Theory (MPT) is diversification.
	 * In a market downturn, MPT disciples believe a well-diversified portfolio will
	 * outperform a concentrated one.
	 * 
	 * @return
	 */
	public static Portfolio getDiversification() {

		throw new UnsupportedOperationException("Variance is not supported by this implementation");
	}

	/**
	 * Non-Correlating Assets
	 * 
	 * Stock portfolios that include 12, 18 or even 30 stocks can eliminate most, if
	 * not all, unsystematic risk, according to some financial experts.
	 * Unfortunately, systematic risk is always present. However, by adding
	 * non-correlating asset classes such as bonds, commodities, currencies and real
	 * estate to a group of stocks, the end-result is often lower volatility and
	 * reduced systematic risk due to the fact that non-correlating assets react
	 * differently to changes in the markets compared to stocks. When one asset is
	 * down, another is up.
	 * 
	 * 
	 */
	public static List<Asset> getNonCorrelatingAssets() {
		throw new UnsupportedOperationException(
				"Take all non correlation assets is not supported by this implementation");

	}

	/**
	 * Stop Losses protect against falling <b>share prices</b>. <b>Hard stops</b> involve
	 * triggering the sale of a stock at a <b>fixed price</b> that doesn't change. For
	 * example, when you buy Company A's stock for $10 per share with a hard stop of
	 * $8, the stock is automatically sold if the price drops to $8.
	 * 
	 */

	public static List<Asset> stopLosses() {
		throw new UnsupportedOperationException("Stop losses is not supported by this implementation");
	}
	
	
	/**
	 * Dividends
	 * https://www.investopedia.com/articles/basics/11/5-portfolio-protection-strategies.asp
	 * 
	 */
	

}
