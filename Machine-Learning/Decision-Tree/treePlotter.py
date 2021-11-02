import matplotlib.pyplot as plt

# 定义文本框和箭头格式
decisionNode = dict(boxstyle='sawtooth', fc='0.8')
leafNode = dict(boxstyle='round4', fc='0.8')
arrow_args = dict(arrowstyle='<-')


# 使用文本注解绘制树节点
def plotNode(nodeTxt, cencerPt, parentPt, nodeType):
    createPlot.axl.annotate(nodeTxt,
                            xy=parentPt,
                            xycoords='axes fraction',
                            xytext=cencerPt,
                            textcoords='axes fraction',
                            va='center',
                            ha='center',
                            bbox=nodeType,
                            arrowprops=arrow_args)


# 第一版的createPlot()，之后还需要修改
# def createPlot():
#     fig = plt.figure(1, facecolor='white')
#     fig.clf()
#     createPlot.ax1 = plt.subplot(111, frameon=False)
#     plotNode('a decision  node', (0.5, 0.1), (0.1, 0.5), decisionNode)
#     plotNode('a leaf node', (0.8, 0.1), (0.3, 0.8), leafNode)
#     plt.show()
# createPlot()
# 报错'function' object has no attribute 'axl'，原因未知


# 获取叶节点的数目
def getNumLeafs(mytree):
    numLeafs = 0
    firstStr = list(mytree.keys())[0]
    secondDict = mytree[firstStr]
    for key in secondDict.keys():
        if type(secondDict[key]).__name__ == 'dict':
            numLeafs += getNumLeafs(secondDict[key])
        else:
            numLeafs += 1
    return numLeafs


# mytree = {'no surfacing': {0: 'no', 1: {'flippers': {0: 'no', 1: 'yes'}}}}
# print(getNumLeafs(mytree))


# 获取决策树的层数
def getTreeDepth(mytree):
    maxDepth = 0
    firstStr = list(mytree.keys())[0]
    secondDict = mytree[firstStr]
    for key in secondDict.keys():
        if type(secondDict[key]).__name__ == 'dict':
            thisDepth = 1 + getTreeDepth(secondDict[key])
        else:
            thisDepth = 1
        if thisDepth > maxDepth:
            maxDepth = thisDepth
    return maxDepth


# mytree = {'no surfacing': {0: 'no', 1: {'flippers': {0: 'no', 1: 'yes'}}}}
# print(getTreeDepth(mytree))


# 在父子节点之间填充文本信息
def plotMidText(centerPt, parentPt, txtString):
    xMid = (parentPt[0] - centerPt[0]) / 2.0 + centerPt[0]
    yMid = (parentPt[1] - centerPt[1]) / 2.0 + centerPt[1]
    createPlot.axl.text(xMid, yMid, txtString)


def plotTree(mytree, parentPt, nodeTxt):
    numLeafs = getNumLeafs(mytree)
    depth = getTreeDepth(mytree)
    firstSides = list(mytree.keys())
    firstStr = firstSides[0]  # 划分数据集的特征
    centerPt = (plotTree.xOff +
                (1.0 + float(numLeafs)) / 2.0 / plotTree.totalW, plotTree.yOff
                )  # x轴右移，(1+叶节点数目)/(2*决策树宽度)，y轴不变
    plotMidText(centerPt, parentPt,
                nodeTxt)  # 绘制父子节点之间的文本标签（对于划分数据集的特征，其实没有文本标签）
    plotNode(firstStr, centerPt, parentPt, decisionNode)  # 绘制父节点与指向子节点的箭头
    secondDict = mytree[firstStr]  # 划分数据集特征的取值
    plotTree.yOff = plotTree.yOff - 1.0 / plotTree.totalD  # y轴下移，1/决策树深度
    for key in secondDict.keys():
        # 如果分支下还有子树，调用函数本身，递归完成该分支的绘制
        if type(secondDict[key]).__name__ == 'dict':
            plotTree(secondDict[key], centerPt, str(key))
        # 如果分支下没有子树，仅一种分类结果，绘制叶子节点，添加父子节点之间的文本标签
        else:
            plotTree.xOff = plotTree.xOff + 1.0 / plotTree.totalW  # x轴右移，1/(2*决策树宽度)
            plotNode(secondDict[key], (plotTree.xOff, plotTree.yOff), centerPt,
                     leafNode)  # 绘制叶子节点
            plotMidText((plotTree.xOff, plotTree.yOff), centerPt,
                        str(key))  # 添加父子节点之间的文本标签
    plotTree.yOff = plotTree.yOff + 1.0 / plotTree.totalD  # y轴上移，恢复初始值，确保另外一个分支父节点的位置正确


def createPlot(intree):
    fig = plt.figure(1, facecolor='white')  # 创建一个新图形，前景色为白色
    fig.clf()  # 清空绘图区
    axprops = dict(xticks=[], yticks=[])  # 定义坐标轴（空白）
    createPlot.axl = plt.subplot(111, frameon=False,
                                 **axprops)  # 创建子图，无边框，无坐标值
    plotTree.totalW = float(getNumLeafs(intree))  # 设置全局变量，记录决策树的宽带
    plotTree.totalD = float(getTreeDepth(intree))  # 设置全局变量，记录决策树的深度
    plotTree.xOff = -0.5 / plotTree.totalW  # 设置全局变量，追踪节点的x轴坐标
    plotTree.yOff = 1.0  # 设置全局变量，追踪节点的y轴坐标
    plotTree(intree, (0.5, 1.0), '')
    plt.show()


# mytree = {'no surfacing': {0: 'no', 1: {'flippers': {0: 'no', 1: 'yes'}}}}
# createPlot(mytree)
# mytree = {'no surfacing': {1: {'flippers': {0: 'no', 1: 'yes'}}, 0: 'no'}}
# createPlot(mytree)import matplotlib.pyplot as plt

# 定义文本框和箭头格式
decisionNode = dict(boxstyle='sawtooth', fc='0.8')
leafNode = dict(boxstyle='round4', fc='0.8')
arrow_args = dict(arrowstyle='<-')


# 使用文本注解绘制树节点
def plotNode(nodeTxt, cencerPt, parentPt, nodeType):
    createPlot.axl.annotate(nodeTxt,
                            xy=parentPt,
                            xycoords='axes fraction',
                            xytext=cencerPt,
                            textcoords='axes fraction',
                            va='center',
                            ha='center',
                            bbox=nodeType,
                            arrowprops=arrow_args)


# 第一版的createPlot()，之后还需要修改
# def createPlot():
#     fig = plt.figure(1, facecolor='white')
#     fig.clf()
#     createPlot.ax1 = plt.subplot(111, frameon=False)
#     plotNode('a decision  node', (0.5, 0.1), (0.1, 0.5), decisionNode)
#     plotNode('a leaf node', (0.8, 0.1), (0.3, 0.8), leafNode)
#     plt.show()
# createPlot()
# 报错'function' object has no attribute 'axl'，原因未知


# 获取叶节点的数目
def getNumLeafs(mytree):
    numLeafs = 0
    firstStr = list(mytree.keys())[0]
    secondDict = mytree[firstStr]
    for key in secondDict.keys():
        if type(secondDict[key]).__name__ == 'dict':
            numLeafs += getNumLeafs(secondDict[key])
        else:
            numLeafs += 1
    return numLeafs


# mytree = {'no surfacing': {0: 'no', 1: {'flippers': {0: 'no', 1: 'yes'}}}}
# print(getNumLeafs(mytree))


# 获取决策树的层数
def getTreeDepth(mytree):
    maxDepth = 0
    firstStr = list(mytree.keys())[0]
    secondDict = mytree[firstStr]
    for key in secondDict.keys():
        if type(secondDict[key]).__name__ == 'dict':
            thisDepth = 1 + getTreeDepth(secondDict[key])
        else:
            thisDepth = 1
        if thisDepth > maxDepth:
            maxDepth = thisDepth
    return maxDepth


# mytree = {'no surfacing': {0: 'no', 1: {'flippers': {0: 'no', 1: 'yes'}}}}
# print(getTreeDepth(mytree))


# 在父子节点之间填充文本信息
def plotMidText(centerPt, parentPt, txtString):
    xMid = (parentPt[0] - centerPt[0]) / 2.0 + centerPt[0]
    yMid = (parentPt[1] - centerPt[1]) / 2.0 + centerPt[1]
    createPlot.axl.text(xMid, yMid, txtString)


def plotTree(mytree, parentPt, nodeTxt):
    numLeafs = getNumLeafs(mytree)
    depth = getTreeDepth(mytree)
    firstSides = list(mytree.keys())
    firstStr = firstSides[0]  # 划分数据集的特征
    centerPt = (plotTree.xOff +
                (1.0 + float(numLeafs)) / 2.0 / plotTree.totalW, plotTree.yOff
                )  # x轴右移，(1+叶节点数目)/(2*决策树宽度)，y轴不变
    plotMidText(centerPt, parentPt,
                nodeTxt)  # 绘制父子节点之间的文本标签（对于划分数据集的特征，其实没有文本标签）
    plotNode(firstStr, centerPt, parentPt, decisionNode)  # 绘制父节点与指向子节点的箭头
    secondDict = mytree[firstStr]  # 划分数据集特征的取值
    plotTree.yOff = plotTree.yOff - 1.0 / plotTree.totalD  # y轴下移，1/决策树深度
    for key in secondDict.keys():
        # 如果分支下还有子树，调用函数本身，递归完成该分支的绘制
        if type(secondDict[key]).__name__ == 'dict':
            plotTree(secondDict[key], centerPt, str(key))
        # 如果分支下没有子树，仅一种分类结果，绘制叶子节点，添加父子节点之间的文本标签
        else:
            plotTree.xOff = plotTree.xOff + 1.0 / plotTree.totalW  # x轴右移，1/(2*决策树宽度)
            plotNode(secondDict[key], (plotTree.xOff, plotTree.yOff), centerPt,
                     leafNode)  # 绘制叶子节点
            plotMidText((plotTree.xOff, plotTree.yOff), centerPt,
                        str(key))  # 添加父子节点之间的文本标签
    plotTree.yOff = plotTree.yOff + 1.0 / plotTree.totalD  # y轴上移，恢复初始值，确保另外一个分支父节点的位置正确


def createPlot(intree):
    fig = plt.figure(1, facecolor='white')  # 创建一个新图形，前景色为白色
    fig.clf()  # 清空绘图区
    axprops = dict(xticks=[], yticks=[])  # 定义坐标轴（空白）
    createPlot.axl = plt.subplot(111, frameon=False,
                                 **axprops)  # 创建子图，无边框，无坐标值
    plotTree.totalW = float(getNumLeafs(intree))  # 设置全局变量，记录决策树的宽带
    plotTree.totalD = float(getTreeDepth(intree))  # 设置全局变量，记录决策树的深度
    plotTree.xOff = -0.5 / plotTree.totalW  # 设置全局变量，追踪节点的x轴坐标
    plotTree.yOff = 1.0  # 设置全局变量，追踪节点的y轴坐标
    plotTree(intree, (0.5, 1.0), '')
    plt.show()


# mytree = {'no surfacing': {0: 'no', 1: {'flippers': {0: 'no', 1: 'yes'}}}}
# createPlot(mytree)
# mytree = {'no surfacing': {1: {'flippers': {0: 'no', 1: 'yes'}}, 0: 'no'}}
# createPlot(mytree)