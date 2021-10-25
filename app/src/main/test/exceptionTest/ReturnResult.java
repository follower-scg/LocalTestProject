package main.test.exceptionTest;

import com.sunyard.frame.base.entity.ReturnFile;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ReturnResult {

    private Integer total;
    
    private List<?> root;

    /**
     * 树返回的时候，存放返回数据
     */
    private List<?> children;
    
    private Boolean success;

    private String message;

    private String errorCode;
    
    private Object object;

    /**
     * 返回文件
     */
    private ReturnFile file;



    /**
     * 视图路径
     */
    private String viewName;

    /**
     * 返回参数，当视图路径不为空时，该参数会被放入ModelMap（request域）
     */
    private Map<String, Object> data;
    
    public ReturnResult(){}
    
    public ReturnResult(Boolean success, String message){
        this.success = success;
        this.message = message;
    }
    
    public ReturnResult(Boolean success, String errorCode, String message){
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
    }
    
    public ReturnResult(Boolean success, Integer total, List<?> root, String message){
        this.success = success;
        this.message = message;
        this.total = total;
        this.root = root;
    }
    
    public ReturnResult(Boolean success, Map<String, Object> data, String message){
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    public ReturnResult(Boolean success, Map<String, Object> data){
        this.success = success;
        this.data = data;
    }
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }



    public ReturnResult(Boolean success){
        this.success = success;
    }
    
    public static ReturnResult success() {
        return success("");
    }
    
    public static ReturnResult success(String msg) {
        if (StringUtils.isEmpty(msg)) {
            msg = "操作成功";
        }
        return (new ReturnResult(true, msg));
    }
    
    public static ReturnResult success(Integer total, List<?> root) {
        String msg = "-";
        return (new ReturnResult(true, total, root, msg));
    }
    
    public static ReturnResult success(Map<String, Object> data) {
        return (new ReturnResult(true, data,"操作成功"));
    }

    public static ReturnResult success(Map<String, Object> retData, String msg) {
        if (StringUtils.isEmpty(msg)) {
            msg = "操作成功";
        }
        return (new ReturnResult(true, retData, msg));
    }
    
    public static ReturnResult failure() {
        return failure("");
    }
    
    public static ReturnResult failure(String msg) {
        if (StringUtils.isEmpty(msg)) {
            msg = "操作失败";
        }
        return (new ReturnResult(false, 0, null, msg));
    }

    public static ReturnResult failure(IErrorCode iErrorCode) {
        if (StringUtils.isEmpty(iErrorCode.getMessage())) {
            iErrorCode.setMessage("操作失败");
        }
        return (new ReturnResult(false, 0, null, iErrorCode.getMessage()));
    }
    
    public static ReturnResult failure(String errorCode, String msg) {
        if (StringUtils.isEmpty(msg)) {
            msg = "操作失败";
        }
        return (new ReturnResult(false, errorCode, msg));
    }
    
    //*****************************************************


    public ReturnFile getFile() {
        return file;
    }

    public void setFile(ReturnFile file) {
        this.file = file;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getTotal() {
        return total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    
    public List<?> getRoot() {
        return root;
    }
    
    public void setRoot(List<?> root) {
        this.root = root;
    }

    public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String toJson(){
    	JSONObject obj = new JSONObject();
    	obj.put("total", total);
    	obj.put("root", root);
    	obj.put("success", success);
    	obj.put("message", message);
    	obj.put("errorCode", errorCode);
    	obj.put("object", object);
    	obj.put("data", data);
    	return obj.toString();
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
