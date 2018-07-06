<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>IBDP</title>
    <sx:head />
    <style type="text/css">
    h2{margin: 20px 0px; padding: 0px; text-align: center; color: rgb(0, 0, 0); font-family: 'Microsoft Yahei', '冬青黑体简体中文 w3'; font-size: 20px; line-height: 30px; widows: auto;}
	h3{margin: 20px 0px; padding: 0px; text-align: center; color: rgb(0, 0, 0); font-family: 'Microsoft Yahei', '冬青黑体简体中文 w3'; font-size: 18px; line-height: 30px;text-align: left; text-indent: 2em;}
	.p{margin: 0px 0px 20px; padding: 0px; color: rgb(51, 51, 51); font-family: 'microsoft yahei'; font-size: 16px; line-height: 30px;text-indent: 2em;}
	.p1{margin: 0px 60px 20px; padding: 0px; color: rgb(51, 51, 51); font-family: 'microsoft yahei'; font-size: 16px; line-height: 30px;}
	.img{margin: 0px 0px 20px; padding: 0px; color: rgb(51, 51, 51); font-family: 'microsoft yahei'; font-size: 16px; line-height: 30px;text-align: center;}
	.img img{max-width:70%;}
</style>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta charset="utf-8">
		<title>Search Results - Ace Admin</title>

		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css">

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="assets/css/select2.min.css">
		<link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css">

		<!-- text fonts -->
		<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css">

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="assets/css/ace-skins.min.css">
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css">

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="assets/js/ace-extra.min.js"></script><style>@keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-moz-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-webkit-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-ms-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}@-o-keyframes nodeInserted{from{outline-color:#fff}to{outline-color:#000}}.ace-save-state{animation-duration:10ms;-o-animation-duration:10ms;-ms-animation-duration:10ms;-moz-animation-duration:10ms;-webkit-animation-duration:10ms;animation-delay:0s;-o-animation-delay:0s;-ms-animation-delay:0s;-moz-animation-delay:0s;-webkit-animation-delay:0s;animation-name:nodeInserted;-o-animation-name:nodeInserted;-ms-animation-name:nodeInserted;-moz-animation-name:nodeInserted;-webkit-animation-name:nodeInserted}</style>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
		<script src="assets/js/jquery-2.1.4.min.js"></script>
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/tree.min.js"></script>
		<script src="assets/js/select2.min.js"></script>
		<script src="assets/js/jquery-ui.custom.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/holder.min.js"></script>
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
			
				//data for tree element
				var category = {
					'for-sale' : {text: 'For Sale', type: 'folder'}	,
					'vehicles' : {text: 'Vehicles', type: 'item'}	,
					'rentals' : {text: 'Rentals', type: 'item'}	,
					'real-estate' : {text: 'Real Estate', type: 'item'}	,
					'pets' : {text: 'Pets', type: 'item'}	,
					'tickets' : {text: 'Tickets', type: 'item'}
				}
				category['for-sale']['additionalParameters'] = {
					'children' : {
						'appliances' : {text: 'Appliances', type: 'item'},
						'arts-crafts' : {text: 'Arts & Crafts', type: 'item'},
						'clothing' : {text: 'Clothing', type: 'item'},
						'computers' : {text: 'Computers', type: 'item'},
						'jewelry' : {text: 'Jewelry', type: 'item'},
						'office-business' : {text: 'Office', type: 'item'},
						'sports-fitness' : {text: 'Sports & Fitness', type: 'item'}
					}
				}
				
				var dataSource1 = function(options, callback){
					var $data = null
					if(!("text" in options) && !("type" in options)){
						$data = category;//the root tree
						callback({ data: $data });
						return;
					}
					else if("type" in options && options.type == "folder") {
						if("additionalParameters" in options && "children" in options.additionalParameters)
							$data = options.additionalParameters.children || {};
						else $data = {}//no data
					}
					
					callback({ data: $data })
				}
				
				$('#cat-tree').ace_tree({
					dataSource: dataSource1,
					multiSelect: true,
					cacheItems: true,
					'open-icon' : 'ace-icon tree-minus',
					'close-icon' : 'ace-icon tree-plus',
					'itemSelect' : true,
					'folderSelect': false,
					'selected-icon' : 'ace-icon fa fa-check',
					'unselected-icon' : 'ace-icon fa fa-times',
					loadingHTML : '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>'
				});
				
			
				$('.tree-container').ace_scroll({size: 250, mouseWheelLock: true});
				$('#cat-tree').on('closed.fu.tree disclosedFolder.fu.tree', function() {
					$('.tree-container').ace_scroll('reset').ace_scroll('start');	
				});
				
				
				
				//select2 location element
				$('.select2').css('min-width', '150px').select2({allowClear:true});
				
				
				//jQuery ui distance slider
				$( "#slider-range" ).css('width','150px').slider({
					range: true,
					min: 0,
					max: 100,
					values: [ 17, 67 ],
					slide: function( event, ui ) {
						var val = ui.values[$(ui.handle).index()-1] + "";
			
						if( !ui.handle.firstChild ) {
							$("<div class='tooltip bottom in' style='display:none;left:-6px;top:14px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>")
							.prependTo(ui.handle);
						}
						$(ui.handle.firstChild).show().children().eq(1).text(val);
					}
				}).find('span.ui-slider-handle').on('blur', function(){
					$(this.firstChild).hide();
				});
				
			
				//this is for demo only
				$('.thumbnail').on('mouseenter', function() {
					$(this).find('.info-label').addClass('label-primary');
				}).on('mouseleave', function() {
					$(this).find('.info-label').removeClass('label-primary');
				});
				
			
				//toggle display format buttons
				$('#toggle-result-format .btn').tooltip({container: 'body'}).on('click', function(e){
					$(this).siblings().each(function() {
						$(this).removeClass($(this).attr('data-class')).addClass('btn-grey');
					});
					$(this).removeClass('btn-grey').addClass($(this).attr('data-class'));
				});
				
				////////////////////
				//show different search page
				$('#toggle-result-page .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					$('.search-page').parent().addClass('hide');
					$('#search-page-'+which).parent().removeClass('hide');
				});
			});
		</script>
		
		
		<script type="text/javascript">

    var user = '<%=session.getAttribute("user")%>';
     
    // alert(user);
     if( user==null	||typeof user == "undefined" ||user=="null")
     {
    //	 alert(user);
     window.location.href="login.jsp"; 
	 }
</script>
</head>
  
  <body ng-app="" class="no-skin">
		 <%@include file="./template.jsp" %>
		 <%@include file="./top_menu.jsp" %>
		<div class="main-container ace-save-state" id="main-container">
 			
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="hr hr-18 hr-double dotted"></div>
								<div class="widget-box">
									<div class="widget-header widget-header-blue widget-header-flat">
										<h4 class="widget-title lighter">数据分析案例</h4>
										<div class="" style="float:right">
											<ul class="breadcrumb">
												<li>
													<i class="ace-icon fa fa-home home-icon"></i>
													<a href="./basic.jsp">Home</a>
												</li> 					
												<li>
													<a href="./instance.jsp">应用实例</a>
												</li>												
											</ul> 
										</div>																									 
										</div>
										<div class="widget-body">
										<div class="widget-main">										
			<div class="main-content">
				<div class="main-content-inner">					
					<div class="page-content">										
						<div class="row">
							<div class="col-xs-12">
								
								<div>
									<div class="row search-page" id="search-page-1">
										<div class="col-xs-12">
											<div class="row">
											
												<div class="col-xs-12 col-sm-9" style="width:100%">
													<div class="row">
														<div class="search-area well col-xs-12">
															<div>
																<h2><strong>水务数据分析案例</strong></h2>																															
															</div>													
														</div>
													</div>
												<div class="row">
														<div class="col-xs-12">
														<p class="p">智慧水务不仅与仪表和传感器相关，“智慧”的实现应当是在一个更庞大的系统中，将水务运营工艺与操作流程数字化，并通过大数据、云计算等技术将信息在数字层面挖掘水务公司资产的价值提升潜力。</p>
														<p class="p">水务数据分析就是结合当下流行的大数据分析等技术(Hadoop、HDFS、Hive、Sqoop、R语言与RServer、Weka学习库等等)，以及机器学习等方法，推动水务迈入大数据时代。把水务信息与数十个关于住房地点、房龄、气候和入住率的数据点整合到一起进行分析、预测，为用户提供水务互联网服务（如：给用户提供个性化用水报告、为节水行为打分、提示花园灌溉许可日期和许可量），也可大大节省用水量。</p>
														<p class="p">操作实现：</p>
														<p class="p">(1)新建项目</p>
														<p class="p">第一步：可以点击项目菜单下的新建项目开始，点击后会跳转到新建项目的界面</p>
														<p class="img">
														<img alt="" src="img/create.png">
														</p>
														<p class="p">第二步：在上传文件时，可以上传新的文件，也可以选择已上传的文件，根据自己的需求变化，本例中上传了新的文件。</p>
														<p class="img">
														<img alt="" src="img/create2.jpg">
														</p>
														<p class="p">第三步：选择模型时可以选择已有模型，可以选择已有算法或上传自己的算法构成新的模型。本例中选择已有算法构成新的模型。</p>
														<p class="img">
														<img alt="" src="img/create3.png">
														</p>
														<p class="p">第四步：不同的算法需要加入的参数不同。例如本例中使用的算法——相关性计算，需要选择相关性算法和所要计算的列，这样可以点击预览进行选择。</p>
														<p class="img">
														<img alt="" src="img/create5.png">
														</p>
														<p class="p">第五步：点击预览后，可以选择几列数据或全部数据进行分析，点击确认即可。选择的列会在输入框中展示出来，可以将算法和数据保存为模型，也可以直接提交。</p>														
														<p class="img">
														<img alt="" src="img/create6.png">
														</p>
														<p class="p">在点击提交之后，会提示“您的分析任务已被提交，点击跳转到项目列表页面”。这时，分析结果可以在数据中找到，在目录中找到并点击项目名，右边表里会出现对应的数据和结果。</p>
														<p class="img">
														<img alt="" src="img/create9.jpg">
														</p>
														<p class="p">(2)预测结果如下：</p>
														<p class="img">
														<img alt="" src="img/create7.jpg">
														</p>
														<p class="img">
														<img alt="" src="img/create8.png">
														</p>
														<div style="margin-left: 65%">
														<h5 class="media-heading"><i class="ace-icon fa fa-hand-o-right"></i><i class="ace-icon fa fa-hand-o-right"></i><i class="ace-icon fa fa-hand-o-right"></i>
															<a href="/IBDP/instance.jsp" class="blue">———点击此处返回用户实例——————</a>
														</h5>
														</div>
														</div>				
													</div>
													<div class="space-12"></div>
													<div id="dataTables-ModelLibrary_wrapper" class="dataTables_wrapper form-inline" role="grid">
																																											
													<!-- 	<div class="modal fade" id="2015yishuichang" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
														    <div class="modal-dialog" style="width: 65%">
														        <div style="text-align:center;background-color: #F5F5F5 ;height:40px;color: #1d6fa6;font-size: large">
																	<label style="padding: 5px;float:center;font-size:18px">数据集介绍~水质检测指标相关性分析 </label>
																	<button class="btn btn-sm pull-right" style="width:50px;padding: 9.5px;" data-dismiss="modal"><i class="ace-icon glyphicon glyphicon-remove"></i></button>
																</div>
														        <div class="modal-content">
														            <div class="col-sm-12">
																		<h3>包含的属性有：</h3>
																		<div>
																			<table class="table table-bordered" style="font-size:14px">
									  											<thead>
									                                               	<tr><th>属性</th><th>取值介绍</th></tr>
																				</thead>
																				<tbody>
																				    <tr>
																				      <td>id</td><td>唯一标识一行数据的凭证，可按照时间作为索引。</td>
																				   </tr>
																				    <tr>
																				      <td>x0137</td><td>pH取值，限值：0~14，若超出则可能数据采集器出故障</td>
																				    </tr>
																				    <tr>
																				      <td>x0205</td><td>总硬度值</td>
																				    </tr>
																				    <tr>
																				      <td>x0207</td><td>氯化物，单位：μg/L</td>
																				     </tr>
																				 </tbody>
																			 </table>
																		</div>
																	</div>
																	
																		<div class="col-sm-12">
																			
																			<h3>数据示例</h3>
									  											<table class="table table-bordered" style="font-size:14px">
									  											<thead>
									                                               	<tr>
									                                                	<th>id</th><th>指标1</th><th>指标2</th><th>指标3</th><th>指标4</th>
									   												</tr>
																				</thead>
																				<tbody>
																				    <tr>
																				      <td>2012-01-02</td><td>0.5</td><td>4</td><td>43</td><td>0.6</td>												      
																				    </tr>
																				    <tr>
																				      <td>2012-02-02</td><td>0.6</td><td>8</td> <td>38</td><td>0.8</td>												      
																				    </tr>
																				    <tr>
																				      <td>2012-03-02</td><td>0.4</td><td>7</td><td>64</td><td>0.5</td>											      
																				    </tr>
																				    <tr>
																				      <td>2012-04-02</td><td>0.8</td><td>7</td><td>54</td><td>0.2</td>												      
																				    </tr>
																				 </tbody>
																			</table>
																		</div>
																	<div class="col-sm-12">
																		<p><font color="red" size="3">注意：数据集中请不要包含空值，可使用本平台“缺失值处理”函数对所有列进行空值处理。</font></p>
																		</div>
																	<div class="modal-footer">
                        												
                    												</div>		
																			
																</div>
														    </div>
														</div>
														<div class="modal fade" id="2015yishuichang" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
														    <div class="modal-dialog" style="width: 65%">
														        <div class="modal-content">
														            
																	<div class="modal-footer">
                        											</div>		
																			
																</div>
														    </div>
														</div>
														<div class="modal fade" id="2015yishuichangjieguo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
														    <div class="modal-dialog" style="width: 65%">
														        <div style="text-align:center;background-color: #F5F5F5 ;height:40px;color: #1d6fa6;font-size: large">
																	<label style="padding: 5px;float:center;font-size:18px">指标相关性结果展示 </label>
																	<button class="btn btn-sm pull-right" style="width:50px;padding: 9.5px;" data-dismiss="modal"><i class="ace-icon glyphicon glyphicon-remove"></i></button>
																</div>
														        <div class="modal-content">
														            	<div style="width:100%;">
  	
  	
																		  	<div id="showPicture" style="width:100%;margin:20px auto;display: flex;
																		  justify-content: center;
																		  align-items: center;  ">
																											
																		  		<img src="/user/2015yishuichang.png" />
																		 	 </div>
																		  	
																		</div>	
																	<div class="modal-footer">
                        											</div>		
																			
																</div>
														    </div>
														</div>
														<div class="modal fade" id="2015yishuichang" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
														    <div class="modal-dialog" style="width: 65%">
														        <div class="modal-content">
														            
																	<div class="modal-footer">
                        											</div>		
																			
																</div>
														    </div>
														</div>
														</div> -->
													<div class="space-12"></div>
												<!-- 	<div class="widget-box"> -->
														<!-- <div class="widget-header widget-header-blue widget-header-flat">
															<h4 class="widget-title lighter">水务大数据分析</h4>
														</div> -->
												<!-- 	<br>	 -->												
													<!-- <div style="margin-left: 65%">
														<h5 class="media-heading"><i class="ace-icon fa fa-hand-o-right"></i><i class="ace-icon fa fa-hand-o-right"></i><i class="ace-icon fa fa-hand-o-right"></i>
															<a href="http://211.87.227.91:18080/waterCC" class="blue">———点击此处查看详情——————</a>
														</h5>
													</div> -->
												<!-- 	<br>
													</div> -->

													
												</div>
											</div>
										</div>
									</div>
								</div>

								

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			

			
		</div>
        </div>
		 
	</div>
	</div>
	</div>				
	</div>
	</div>
								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

			<div class="footer">
				<div class="footer-inner">
					<div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">SDU</span>
							数据分析平台 &copy; 2017-2018
						</span>

						&nbsp; &nbsp;
						<span class="action-buttons">
							<a href="#">
								<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
							</a>

							<a href="#">
								<i class="ace-icon fa fa-rss-square orange bigger-150"></i>
							</a>
						</span>
					</div>
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>

  </body>
</html>
