var algorithmJSONList=[
[//数据清理算法
	{
    				name:'数据缺失值处理',
    				actionName:'DplyrMethods',
    				method:'na',
    				introduction:'数据缺失值处理',
					guide:'输入你要处理缺失值的列号',
    				param:[
    					{name:'依据列',field:'NaVariable',type:'String',selectable:true,linkvalue:'column',linkchar:','}//selectable用于标明该参数是否支持预览数据然后选择复选框填充参数 ，selectvalue用于指明被拼接的值是表头内容还是列号，linkchar用于指明数据列的拼接方式（用逗号还是加号拼接，或者其他符号也行）,如果linkchar没识别就默认使用加号'+'进行拼接					
    				]
    			},
    			{
    				name:'变量选择（select）',
    				actionName:'DplyrMethods',
    				method:'select',
					guide:'输入某一列名作为参数来选择子数据集',
    				introduction:'变量选择是数据处理的一种方法',
    				param:[
    					
    					{name:'选择变量',field:'CollectVariable',type:'String',selectable:true,linkvalue:'column',linkchar:','}
    				]
    			},
    			{
    				name:'变量筛选（filter）',
    				actionName:'DplyrMethods',
    				method:'filter',
					guide:'输入判断条件语句来选择数据集，如属性a==8，属性b<6等这类的判断条件',
    				introduction:'变量筛选（filter）可以按给定的逻辑条件筛选出符合要求的子数据集',
    				param:[
    					
    					{name:'筛选条件',field:'FilterVariable',type:'String',selectable:false}
    				]
    			},
    			{
    				name:'数据去重（distinct）',
    				actionName:'DplyrMethods',
    				method:'distinct',
					guide:'去重变量输入你想要去除重复值的那一列的属性值，去重返回变量输入你是否将去重后的所有列进行返回还是返回去重后的部分列',
    				introduction:'数据去重可以根据某些变量的组合进行去重',
    				param:[
    					
    					{name:'去重变量',field:'DistinctVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    					
    					{name:'去重后返回变量',field:'ReturnVariable',type:'Select',options:['全部','去重变量所在列'],value:''}
						//
    				]
    			},
    			{
    				name:'数据取样（sample_n）',
    				actionName:'DplyrMethods',
    				method:'sample_n',
    				introduction:'随机抽取指定数目的样本',
					guide:'抽取样本数输入你要抽样的个数，replace等于是表示有放回抽样，false等于否表示无放回抽样',
    				param:[
    					
    					{name:'抽取样本数',field:'SampleNumber',type:'String',selectable:false},
    					
    					{name:'是否有放回抽样',field:'repalce',type:'Select',options:['是','否'],value:''}
    				]
    			},
    			{
    				name:'划分训练集和测试集（sample）',
    				actionName:'DplyrMethods',
    				method:'sample',
					guide:'权重用来划分数据集的比例，输入为小于1的小数，如:(0.5,0.5)',
    				introduction:'可以将数据划分成训练集和测试集',
    				
    				param:[
    				    {name:'权重',filed:'prob',type:'String'}
    				]
    			}
],
[//数据分析算法
				{
						name:'统计量分析',
						actionName:'statisticsMethod',
						introduction:'统计量分析',
						guide:'分析列输入你要进行分析的某一列的列号（1,2...），统计量输入你要分析的统计量',
						param:[
						   	{name:'分析列',field:'column',type:'String',selectable:true,linkvalue:'column',linkchar:','},
							{name:'统计量',field:'s',type:'Checkbox',options:['均值','中位数','众数','极差','标准差','第一四分位数','第三四分位数','变异系数'],value:[]}  		
							
						]
				},
    			
    			{
    				name:'贝叶斯算法',
    				actionName:'BayesMethod',
    				introduction:'R语言中的关于贝叶斯的一种算法',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				]
    			},
    			{
    				name:'相关性计算',
    				based:'R',
    				actionName:'CorrelationMethod',
					guide:'有3种类型的计算相关性的方法，系统默认使用pearson方法。所要计算的列输入你想要计算相关性的列号',
    				introduction:'用于计算相关性的算法',
    				param:[
    				    {name:'相关性算法',field:'chosework',type:'Select',options:['kendall','spearman','pearson'],value:''},
    					{name:'所要计算的列',field:'chosecolumn',type:'String',selectable:true,linkvalue:'column',linkchar:','}
    				]
    			},
    			{
    				name:'决策树(基于R)',
    				based:'R',
    				actionName:'DecisionTreeMethod',
    				Method:'R',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'用于计算相关性的算法',
    				param:[
						{name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
						{name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'} 
       				]
    			},
    			{
    				name:'决策树(基于Spark_MLlib)',
    				based:'Spark',
    				actionName:'DecisionTreeMethod',
    				Method:'spark',
    				introduction:'用于计算相关性的算法',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				param:[
						{name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
						{name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'} 
       				]
    			},
    			{
    				name:'决策树(C4.5)',
    				based:'R',
    				actionName:'DecisionTreeMethod',
    				Method:'c45',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'用于计算相关性的算法',
    				param:[
						{name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
						{name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'} 
       				]
    			},
    			{
    				name:'决策树(C5.0)',
    				based:'R',
    				actionName:'DecisionTreeMethod',
    				Method:'C50',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'用于计算相关性的算法',
    				param:[
       					{name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       					{name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'} 
       				]
    			},
    			{
    				name:'决策树(CART)',
    				based:'R',
    				actionName:'DecisionTreeMethod',
    				Method:'CART',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'用于计算相关性的算法',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'} 
       				]
    			},
    			{
    				name:'KNN',
    				based:'R',
    				actionName:'KNNMethod',
    				Method:'KNN',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用那些属性来预测,近邻个数输入你通过周围几个k个样本来估计你的样本',
    				introduction:'用于计算相关性的算法',
    				param:[
       					{name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       					{name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       					{name:'近邻个数',field:'size',type:'String',selectable:false}
       				]
    			},
    			{
    				name:'SVM',
    				based:'R',
    				actionName:'SVMMethod',
    				Method:'SVM',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'用于计算相关性的算法',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'}   					
       				]
    			},
    			{
    				name:'主成分分析(基于R)',
    				based:'R',
					guide:'输入要进行主成分分析的列的列号，可以输入多列',
    				actionName:'PCAMethod',
    				introduction:'主成分分析就是从事务错综复杂的关系中找出部分主成分进行定量分析，从而实现降维和简化的作用',
    				param:[
       					{name:'所选列',field:'column',type:'String',selectable:true,linkvalue:'column',linkchar:','}       		
       				]
    			},
    			{
    				name:'主成分分析(基于Spark_MLlib)',
    				actionName:'PCAMethod',
    				based:'Spark',
					guide:'输入要进行主成分分析的列的列号，可以输入多列',
    				introduction:'主成分分析就是从事务错综复杂的关系中找出部分主成分进行定量分析，从而实现降维和简化的作用',
    				param:[
       					{name:'所选列',field:'column',type:'String',selectable:true,linkvalue:'column',linkchar:','}       		
       				]
    			},
    			{
    				name:'神经网络',
    				actionName:'NnetMethod',
    				based:'R',
					guide:'1.神经网络可以用来分类和回归，如将图片识别出来确定它是苹果还是橘子属于分类问题，回归问题是通过自变量和因变量来确定变量间的关系，如研究质量和用户满意度之间的关系，这就需要建立回归模型。2.预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测,如:y~.表示用其他全部属性预测y.3.隐藏层介于输入层和输出层之间，用于接收用户的输入的数据并进行处理加工来传给输出层，神经元数量相当于其中的工作节点，设置的太小没办法处理数据，设置太大回耗时太久。可以设置为10左右。4.最大迭代次数输入为想要训练多少次数据，可以设置为100次。',
    				introduction:'BP神经网络是一种按误差逆传播算法训练的多层前馈网络',
    				param:[
    				    {name:'用途',field:'type',type:'Select',options:['分类','回归'],value:''},
    				    {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
 				        {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},   
       					{name:'隐藏层神经元数量',field:'size',type:'String',selectable:false},
       					{name:'最大迭代次数',field:'maxit',type:'String',selectable:false}    		
       				]
    			},
    			{
    				name:'多层感知机（基于Spark_MLlib）×',
    				actionName:'NnetMethod',
    				based:'Spark',
    				introduction:'BP神经网络是一种按误差逆传播算法训练的多层前馈网络',
    				param:[
       				    {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
 				        {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       					{name:'层',field:'size',type:'String',selectable:false},
       					{name:'最大迭代次数',field:'maxit',type:'String',selectable:false}    		
       				]
    			},
    			{
    				name:'随机森林（基于Spark_MLlib）',
    				based:'Spark',
    				actionName:'RandomForestMethod',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'随机森林是一个包含多个决策树的分类器',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},  		
       				]
    			},
    			{
    				name:'Kmeans(基于R)',
    				based:'R',
    				actionName:'KmeansMethod',
					guide:'依据列输入要在哪些属性上进行聚类分析，输入相应的列号，聚类个数输入最后会分成几类。划分成几个簇',
    				introduction:'Kmeans算法接受参数 k ；然后将事先输入的n个数据对象划分为 k个聚类以便使得所获得的聚类满足:同一聚类中的对象相似度较高；而不同聚类中的对象相似度较小',
    				param:[
    				    {name:'依据列',field:'column',type:'String',selectable:true,linkvalue:'column',linkchar:','},   	  
       					{name:'聚类个数',field:'centers',type:'String',selectable:false}   		
       				]
    			},
    			{
    				name:'Kmeans(基于Spark_MLlib)',
    				based:'Spark',
    				actionName:'KmeansMethod',
					guide:'依据列输入要在哪些属性上进行聚类分析，输入相应的列号，聚类个数输入最后会分成几类。划分成几个簇',
    				introduction:'Kmeans算法接受参数 k ；然后将事先输入的n个数据对象划分为 k个聚类以便使得所获得的聚类满足:同一聚类中的对象相似度较高；而不同聚类中的对象相似度较小',
    				param:[
    				    {name:'依据列',field:'column',type:'String',selectable:true,linkvalue:'column',linkchar:','},   	
       					{name:'聚类个数',field:'centers',type:'String',selectable:false}   		
       				]
    			},
    			{
    				name:'层次聚类',
    				based:'R',
    				actionName:'HclusterMethod',
    				introduction:'层次聚类',
    				guide:'层次聚类有4中不同的联结方法:single、complete、average、centroid，average联结通常被认为是最合适的！分割分层聚类选择是的情况下可以选择分割分层的聚类数',
    				param:[
       					{name:'联结方法',field:'metric',type:'Select',options:['single','complete','average','centroid'],value:''},
       					{name:'是否分割分层聚类',field:'cut',type:'Select',options:['是','否'],value:''},//将判断为true的值放在第一个
       					{name:'分割分层聚类数',field:'cutnumber',type:'String',selectable:false,dependon:'2'}//该参数的是否显示依赖于第‘2’个参数的选择。注意！！！ 依赖于的参数（这里指‘是否分割分层聚类’）,其选项必须设置第一个选项为判断为true的情况，否则会报错
       				]
    			},
    			{
    				name:'线性回归(基于R)',
    				based:'R',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				actionName:'RegressionMethod',
    				introduction:'线性回归(Linear Regression)是利用称为线性回归方程的最小平方函数对一个或多个自变量和因变量之间关系进行建模的一种回归分析',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       				]
    			},
    			{
    				name:'线性回归(基于Spark_MLlib)',
    				based:'Spark',
    				actionName:'RegressionMethod',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'线性回归(Linear Regression)是利用称为线性回归方程的最小平方函数对一个或多个自变量和因变量之间关系进行建模的一种回归分析',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       				]
    			},
    			{
    				name:'Logistic回归(基于R)',
    				based:'R',
    				actionName:'LogisticMethod',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'logistic回归是一种广义线性回归（generalized linear model）',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       				]
    			},
    			{
    				name:'Logistic回归(基于Spark_MLlib)',
    				based:'Spark',
    				actionName:'LogisticMethod',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				introduction:'logistic回归是一种广义线性回归（generalized linear model）',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       				]
    			},
    			{
    				name:'随机森林(基于Spark_MLlib)',
    				based:'Spark',
					guide:'预测变量输入你要预测的某一列的属性名，.表示其他所有的属性。因子变量输入你要用哪些属性来预测',
    				actionName:'RandomForestMethod',
    				introduction:'随机森林可用于分类或回归',
    				param:[
    				       {name:'预测变量',field:'predictionVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
    				       {name:'因子变量',field:'factorVariable',type:'String',selectable:true,linkvalue:'header',linkchar:'+'},
       				]
    			},
    			{
    				name:'关联规则挖掘',
    				based:'R',
					guide:"1.分隔符输入符号用来分割一条记录，如；',','\t'制表符等. <br />2.项集是项的集合，如集合{牛奶，麦片，糖}是一个3项集。支持度是:包含项集的记录数/总的记录数。这里要求填写最小支持度作为一个最低限制。如:0.2。3.置信度表示规则的可靠程度，如:A发生导致B发生的的可能性是多少.这里要求输入最小置信度。",
    				actionName:'AprioriMethod',
    				introduction:'Apriori算法',
					
    				param:[
       					{name:'分隔符',field:'sep',type:'String',selectable:false},
       					{name:'支持度',field:'support',type:'String',selectable:false},
       					{name:'可信度',field:'confidence',type:'String',selectable:false}
       				]
    			}
    			]];
