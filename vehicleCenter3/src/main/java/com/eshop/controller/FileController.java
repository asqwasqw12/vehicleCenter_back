package com.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.aop.Log;
import com.eshop.common.FileUtil;
import com.eshop.common.HttpResult;
import com.eshop.pojo.FileBean;
import com.eshop.pojo.TreeNode;
import com.eshop.service.FileService;

import java.util.*;

@RestController
@RequestMapping("/file")
public class FileController {
	
@Autowired 
FileService fileService;

//是否开启共享文件模式
public static Boolean isShareFile = false;

public static long treeId = 0;

//新建文件夹、修改文件夹、修改文件名
@Log("保存文件")
@PreAuthorize("hasAuthority('file:list:add') AND hasAuthority('file:list:edit')")
@PostMapping(value="/save")
public HttpResult save(@RequestBody FileBean fileBean) {
	fileService.save(fileBean);
	return HttpResult.ok();
}

@Log("查看文件树")
@PreAuthorize("hasAuthority('file:list:view')")
@GetMapping(value="/findTree")
public HttpResult findTree(@RequestParam String name){
	if(name !=null && name !="") {
		return HttpResult.ok(fileService.findTree(name));
	}else {
		return HttpResult.ok(fileService.findTree());
	}
		/*
		 * fileBean.setFilePath(FileUtil.urlDecode(fileBean.getFilePath()));
		 * List<FileBean> fileList = fileService.selectFileList(fileBean); return
		 * HttpResult.ok(fileList);
		 */
}

@Log("删除文件")
@PreAuthorize("hasAuthority('file:list:delete')")
@PostMapping(value="/delete")
public HttpResult delete(@RequestBody List<FileBean> records) {
	fileService.delete(records);
	return HttpResult.ok();
}

@Log("上传文件")
@PreAuthorize("hasAuthority('file:list:add') AND hasAuthority('file:list:edit')")
@PostMapping(value="/upload")
public HttpResult upload(@RequestParam String name,@RequestParam Long parentId, @RequestParam("file") MultipartFile file) {
	fileService.upload(name,parentId,file);
	return HttpResult.ok();
}

//@Log("移动文件")
//@PreAuthorize("hasAuthority('file:list:add') AND hasAuthority('file:list:edit')")
//@PostMapping(value="/movefile")
//public HttpResult moveFile(@RequestBody FileBean fileBean) {
//	
//    String oldfilepath = fileBean.getOldFilePath();
//    String newfilepath = fileBean.getNewFilePath();
//    String filename = fileBean.getName();
//    String extendname = fileBean.getExtendName();
//
//    fileService.updateFilepathByFilepath(oldfilepath, newfilepath, filename, extendname);
//    return HttpResult.ok();
//}
//
//@Log("获取文件树")
//@PreAuthorize("hasAuthority('file:list:view')")
//@GetMapping(value="/getFileTree")
//public HttpResult getFileTree(){
//	FileBean fileBean = new FileBean();
//	Long publicFileId=(long) 2;			//公共文件夹的userId等于2
//	fileBean.setUserId(publicFileId);
//    List<FileBean> filePathList = fileService.selectFilePathTreeByUserId(fileBean);
//    TreeNode resultTreeNode = new TreeNode();
//    resultTreeNode.setNodeName("/");
//
//    for (int i = 0; i < filePathList.size(); i++){
//        String filePath = filePathList.get(i).getFilePath() + filePathList.get(i).getName() + "/";
//
//        Queue<String> queue = new LinkedList<>();
//
//        String[] strArr = filePath.split("/");
//        for (int j = 0; j < strArr.length; j++){
//            if (!"".equals(strArr[j]) && strArr[j] != null){
//                queue.add(strArr[j]);
//            }
//
//        }
//        if (queue.size() == 0){
//            continue;
//        }
//        resultTreeNode = insertTreeNode(resultTreeNode,"/", queue);
//
//
//    }
//    return HttpResult.ok(resultTreeNode);
//
//}
//
//public TreeNode insertTreeNode(TreeNode treeNode, String filepath, Queue<String> nodeNameQueue){
//
//    List<TreeNode> childrenTreeNodes = treeNode.getChildren();
//    String currentNodeName = nodeNameQueue.peek();
//    if (currentNodeName == null){
//        return treeNode;
//    }
//
//    Map<String, String> map = new HashMap<>();
//    filepath = filepath + currentNodeName + "/";
//    map.put("filepath", filepath);
//
//    if (!isExistPath(childrenTreeNodes, currentNodeName)){  //1、判断有没有该子节点，如果没有则插入
//        //插入
//        TreeNode resultTreeNode = new TreeNode();
//
//
//        resultTreeNode.setAttributes(map);
//        resultTreeNode.setNodeName(nodeNameQueue.poll());
//        resultTreeNode.setId(treeId++);
//
//        childrenTreeNodes.add(resultTreeNode);
//
//    }else{  //2、如果有，则跳过
//        nodeNameQueue.poll();
//    }
//
//    if (nodeNameQueue.size() != 0) {
//        for (int i = 0; i < childrenTreeNodes.size(); i++) {
//
//            TreeNode childrenTreeNode = childrenTreeNodes.get(i);
//            if (currentNodeName.equals(childrenTreeNode.getLabel())){
//                childrenTreeNode = insertTreeNode(childrenTreeNode, filepath, nodeNameQueue);
//                childrenTreeNodes.remove(i);
//                childrenTreeNodes.add(childrenTreeNode);
//                treeNode.setChildNode(childrenTreeNodes);
//            }
//
//        }
//    }else{
//        treeNode.setChildNode(childrenTreeNodes);
//    }
//
//    return treeNode;
//
//}
//
//public boolean isExistPath(List<TreeNode> childrenTreeNodes, String path){
//    boolean isExistPath = false;
//
//    try {
//        for (int i = 0; i < childrenTreeNodes.size(); i++){
//            if (path.equals(childrenTreeNodes.get(i).getLabel())){
//                isExistPath = true;
//            }
//        }
//    }catch (Exception e){
//        e.printStackTrace();
//    }
//
//
//    return isExistPath;
//}

}
