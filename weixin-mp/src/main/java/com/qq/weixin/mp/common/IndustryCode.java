package com.qq.weixin.mp.common;

/**
 * 行业代码查询
 * 
 * @author Y13
 *
 */
public enum IndustryCode {

    IT_INTERNET("IT科技", "互联网/电子商务", "1"),
    IT_SOFTWARE("IT科技", "IT软件与服务", "2"),
    IT_HARDWARE("IT科技", "IT硬件与设备", "3"),
    IT_ELECTRONIC("IT科技", "电子技术", "4"),
    IT_COMMUNICATOR("IT科技", "通信与运营商", "5"),
    IT_ONLINE_GAME("IT科技", "网络游戏", "6"),
    
    FI_BANK("金融业", "银行", "7"),
    FI_FUND("金融业", "基金|理财|信托", "8"),
    FI_INSURANCE("金融业", "保险", "9"),
    
    FOOD("餐饮", "餐饮", "10"),
    
    HT_HOTEL("酒店旅游", "酒店", "11"),
    HT_TRAVEL("酒店旅游", "旅游", "12"),
    
    TW_EXPRESS("运输与仓储", "快递", "13"),
    TW_LOGISTICS("运输与仓储", "物流", "14"),
    TW_WAREHOUSING("运输与仓储", "仓储", "15"),
    
    EDU_TRAINING("教育", "培训", "16"),
    EDU_SCHOOL("教育", "院校", "17"),
    
    GU_ACADEMIC("政府与公共事业", "学术科研", "18"),
    GU_TRAFFIC("政府与公共事业", "交警", "19"),
    GU_MUSEUM("政府与公共事业", "博物馆", "20"),
    GU_NONPROFIT("政府与公共事业", "公共事业|非盈利机构", "21"),
    
    MC_MEDICAL("医药护理", "医药医疗", "22"),
    MC_CARE_BEAUTY("医药护理", "护理美容", "23"),
    MC_HEALTH("医药护理", "保健与卫生", "24"),
    
    TT_CAR("交通工具", "汽车相关", "25"),
    TT_MOTORCYCLE("交通工具", "摩托车相关", "26"),
    TT_TRAIN("交通工具", "火车相关", "27"),
    TT_AIRPLANE("交通工具", "飞机相关", "28"),
    
    RE_BUILDING("房地产", "建筑", "29"),
    RE_PROPERTY("房地产", "物业", "30"),
    
    CG_GOODS("消费品", "消费品", "31"),
    
    BS_LEGAL("商业服务", "法律", "32"),
    BS_EXHIBITION("商业服务", "会展", "33"),
    BS_INTERMEDIARY("商业服务", "中介服务", "34"),
    BS_CERTIFICATION("商业服务", "认证", "35"),
    BS_AUDIT("商业服务", "审计", "36"),
    
    SE_MEDIA("文体娱乐", "传媒", "37"),
    SE_PHYSICAL_EDUCATION("文体娱乐", "体育", "38"),
    SE_ENTERTAINMENT("文体娱乐", "娱乐休闲", "39"),
    
    PT_PRINT("印刷", "印刷", "40"),
    
    OR_OTHER("其它", "其它", "41");
    
    /**
     * 主行业
     */
    public final String industry;
    
    /**
     * 副行业
     */
    public final String deputy;
    
    /**
     * 行业编码
     */
    public final String code;
    
    private IndustryCode(String industry, String deputy, String code) {
        this.industry = industry;
        this.deputy = deputy;
        this.code = code;
    }
}
