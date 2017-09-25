package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;

/**
 * 弹出拍照或者相册发图的事件推送
 * 
 * <Event><![CDATA[pic_photo_or_album]]></Event>
 * <EventKey><![CDATA[6]]></EventKey>
 * <SendPicsInfo>
 *   <Count>1</Count>
 *   <PicList>
 *     <item>
 *       <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>
 *     </item>
 *   </PicList>
 * </SendPicsInfo>
 * 
 * @author Y13
 *
 */
@MpEvent("pic_photo_or_album")
@JacksonXmlRootElement(localName="xml")
public class PicPhotoOrAlbumEvent extends PicSysphotoEvent {

}
