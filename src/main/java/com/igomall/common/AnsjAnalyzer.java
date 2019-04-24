
package com.igomall.common;

/**
 * Analyzer - Ansj
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public class AnsjAnalyzer extends org.ansj.lucene5.AnsjAnalyzer {

	/**
	 * 默认类型
	 */
	public static final TYPE DEFAULT_TYPE = TYPE.dic_ansj;

	/**
	 * 构造方法
	 */
	public AnsjAnalyzer() {
		super(DEFAULT_TYPE);
	}

}