package shotgun.my.sweetutil.workflow.entity;

/**
 * 节点
 *
 * @author wulm
 **/
public class FlowNode {

    /*
{
"initArgs(初始化json对象,自动设值)":{},
"curNode(自动设值,脚本可手动修改)":{
            "url":"xxx",
            "method":"xxx",
            "headers":"xxx",
            "body":"xxx(http原始body，form提交可能需要使用HttpClientUtil.buildFormString处理)"
            },

"result(当前的执行结果字符串，自动设值)":"",
"parseResult(上一个节点的解析结果,脚本可手动修改)(可为对象)":"",

"storage(流程持久数据,自动设值,脚本可手动修改)":{

}

}
    * */

    private String method;
    /**
     * 非必填,不填则不执行请求
     */
    private String url;
    /**
     * 请求头（固定）可被执行前脚本修改
     */
    private String headers;
    /**
     * 请求体（固定）
     */
    private String body;


}
