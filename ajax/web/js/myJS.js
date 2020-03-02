/**
 * my personality javascript
 *
 * @author LinZhenNan  lin_hehe@qq.com 2020/03/01 10:24
 */

/**
 * 获取 XMLHttpRequest 对象
 *
 * @author LinZhenNan  lin_hehe@qq.com 2020/03/01 10:24
 * @returns {XMLHttpRequest}
 */
function getXMLHttpRequest() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;
}

/**
 * 查看 XMLHttpRequest 的属性和状态
 */
function view() {
    // 获取 XMLHttpRequest 对象
    var xmlhttp = getXMLHttpRequest();
    // 处理响应结果
    xmlhttp.onreadystatechange = function () {
        // readyState, status, responseText
        alert("readyState : " + xmlhttp.readyState + "\nstatus : " + xmlhttp.status + "\nresponseText : " + xmlhttp.responseText);
        console.log("readyState : " + xmlhttp.readyState + "\nstatus : " + xmlhttp.status + "\nresponseText : " + xmlhttp.responseText);

        // 响应信息读取完毕
        if (xmlhttp.readyState == 4) {
            // 响应状态为正常
            if (xmlhttp.status == 200) {
                alert("readyState = 4, status = 200");
                console.log("readyState = 4, status = 200");
                // responseHeaders
                alert("responseHeaders : " + xmlhttp.getAllResponseHeaders());
                console.log("responseHeaders : " + xmlhttp.getAllResponseHeaders());
                // responseText
                alert("responseText : " + xmlhttp.responseText);
                console.log("responseText : " + xmlhttp.responseText);
            }
        }
    }
    // 建立一个连接
    xmlhttp.open("get", "login");
    // 设置头信息
    xmlhttp.setRequestHeader("hehe", "heheTest");
    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    // 发送请求
    xmlhttp.send(null);
}