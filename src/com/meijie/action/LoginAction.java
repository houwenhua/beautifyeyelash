package com.meijie.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.meijie.entity.SysUser;
import com.meijie.entity.Tree;
import com.meijie.interfaces.ILoginService;
import com.meijie.utils.AjaxJson;
import com.meijie.utils.DataBasesException;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String loginname;
	private String pwd;
	private String imageText;
	
	public String getImageText() {
		return imageText;
	}

	public void setImageText(String imageText) {
		this.imageText = imageText;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	private ILoginService loginService;
	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	public static final int WIDTH = 85;
	public static final int HEIGHT = 25;
	
	
	//登录
	public String login() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("verificationCode");
		
		SysUser user = new SysUser();
		user.setLoginname(loginname);
		user.setPassword(pwd);
		
		List<SysUser> list = loginService.login(user);
		if(list.size()>0){
			imageText = imageText.toLowerCase();
			if(code.equals(imageText)){
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("user", list.get(0));
				request.setAttribute("username", loginname);
				String userJson = JSON.toJSONString(new Integer(list.size()));
				response.getWriter().write(userJson);
			}else{
				String userJson = JSON.toJSONString(new Integer(200));
				response.getWriter().write(userJson);
			}
		}else{
			String userJson = JSON.toJSONString(new Integer(list.size()));
			response.getWriter().write(userJson);
		}
		return NONE;
	}
	/**
	 * 加载导航栏的树形结构
	 * 
	 * tree
	 * @throws SQLException 
	 * @throws IOException 
	 * */
	public void tree() throws SQLException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String trid = request.getParameter("id");
		
		int treeid = 0;
		if(trid!=null){
			treeid = Integer.parseInt(trid);
		}
		List<Tree> list = new ArrayList<Tree>();
		list = loginService.getTreeList(treeid);
		
		/**
		 * 根据权限获得不同的菜单栏
		 */
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		String jurisdiction = user.getJurisdiction();
		List<Tree> jurisdictionList = new ArrayList<Tree>();
		if("管理员".equals(jurisdiction)){
			//管理员菜单
			for(Tree t : list){
			
			    if("基本信息管理".equals(t.getText())){
					jurisdictionList.add(t);
				}
				if("会员信息".equals(t.getText())){
					jurisdictionList.add(t);
				}
				if("消费记录".equals(t.getText())){
					jurisdictionList.add(t);
				}
				if("会员消费记录".equals(t.getText())){
					jurisdictionList.add(t);
				}
				if("游客消费记录".equals(t.getText())){
					jurisdictionList.add(t);
				}
				if("商品消费记录".equals(t.getText())){
					jurisdictionList.add(t);
				}
			}
			String treeJson = JSON.toJSONString(jurisdictionList);
			response.getWriter().write(treeJson);
		} else {
            //系统管理员的菜单
			String treeJson = JSON.toJSONString(list);
			response.getWriter().write(treeJson);
		}
		
		
		//系统管理员的菜单
		/*String treeJson = JSON.toJSONString(list);
		response.getWriter().write(treeJson);*/
	}
	
	/*
	 * 验证图片
	 * **/
	public String image() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		//1设置背景色
		setGround(g);
		//2设置边框
		setBorder(g);
		//3画干扰线
		drawRandomLine(g);
		//4写随机数
		drawRandomNum(g);
		//5图像写给浏览器
		response.setContentType("image/jpeg");
		//发送头让浏览器不缓存
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		ImageIO.write(image, "jpg", response.getOutputStream());
		return NONE;
	}

	private void setGround(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	private void setBorder(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(1, 1, WIDTH-2, HEIGHT-2);		
	}
	private void drawRandomLine(Graphics g) {
		g.setColor(Color.GREEN);
		for(int i=0;i<5;i++){
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
	}
	private void drawRandomNum(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,20));
		String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int x = 5;
		String num = "";
		for(int i=0;i<4;i++){
			String ch = base.charAt(new Random().nextInt(base.length()))+"";
			g.drawString(ch, x, 20);
			x+=20;
			num+=ch;
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("verificationCode", num.toLowerCase());
	}
	
	/**
	 * 注销登录
	 */
	public String logout(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//session.invalidate();
		session.removeAttribute("user");
		return "logout";
	}
	
	/**
	 * 修改密码
	 * @throws IOException 
	 */
	public void updatePwd() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String pwdone = request.getParameter("pwdone");
		String pwdtwo = request.getParameter("pwdtwo");
		
		SysUser user = (SysUser) session.getAttribute("user");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		try{
			if(pwdone.equals(pwdtwo)){
				user.setPassword(pwdone);
				loginService.updatePassword(user);
				//session.removeAttribute("user");
				session.setAttribute("user", user);
				result.setSuccess(true);
				result.setMsg("修改成功");
			} else {
				result.setMsg("密码不一致");
			}
		} catch(DataBasesException e) {
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		String json = JSON.toJSONString(result);
		response.getWriter().write(json);
	}
}
