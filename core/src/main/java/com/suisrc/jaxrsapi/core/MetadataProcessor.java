package com.suisrc.jaxrsapi.core;

import org.jboss.jandex.Index;

/**
 * 元数据处理器
 * @author Y13
 *
 */
public interface MetadataProcessor {

	/**
	 * 处理元数据
	 * @param index
	 */
	void processMetadata(Index index);

}
