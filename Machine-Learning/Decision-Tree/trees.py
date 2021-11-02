from math import log
import operator


# 计算熵
def calcShannonEnt(dataset):
    num = len(dataset)
    labelCounts = {}
    for featVec in dataset:
        currentLabel = featVec[-1]
        if currentLabel not in labelCounts.keys():
            labelCounts[currentLabel] = 0
        labelCounts[currentLabel] += 1
    shannonEnt = 0
    for key in labelCounts:
        prob = float(labelCounts[key] / num)
        shannonEnt -= prob * log(prob, 2)
    return shannonEnt


# 创建测试数据集
def createDataset():
    dataset = [[1, 1, 'yes'], [1, 1, 'yes'], [1, 0, 'no'], [0, 1, 'no'],
               [0, 1, 'no']]
    # labels是特征的名称
    labels = ['no surfacing', 'flippers']
    return dataset, labels


# 测试
# mydata,labels = createDataset()
# print(mydata)
# print(calcShannonEnt(mydata))
# 修改第一个实例的分类结果为maybe
# mydata[0][-1] = 'maybe'
# print(mydata)
# print(calcShannonEnt(mydata))


# 划分数据集
def splitDataset(dataset, axis, value):
    retDataset = []
    for featVec in dataset:
        if featVec[axis] == value:
            reducedFeatVec = featVec[:axis]
            reducedFeatVec.extend(featVec[axis + 1:])
            retDataset.append(reducedFeatVec)
    return retDataset


# mydata,labels = createDataset()
# print(mydata)
# a = splitDataset(mydata, 0, 0)
# b = splitDataset(mydata, 0, 1)
# c = splitDataset(mydata, 1, 0)
# d = splitDataset(mydata, 1, 1)
# print(a)
# print(b)
# print(c)
# print(d)


# 选择最好的数据集划分方式
def chooseBestFeatureToSplit(dataset):
    numFeatures = len(dataset[0]) - 1
    baseEntropy = calcShannonEnt(dataset)
    bestInfoGain = 0.0
    bestFeature = -1
    # 每个特征计算一次信息增益
    for i in range(numFeatures):
        featList = [example[i] for example in dataset]
        uniqueVals = set(featList)
        newEntropy = 0
        # 根据特征i的取值范围若干组（/子集/分支），计算每个子集的信息熵
        # 累计求和即可得到以特征i分组的数据集（包含所有数据）的新熵值
        # 新熵值小于原始数据集的熵值，因为数据更“有序”
        for value in uniqueVals:
            subDataset = splitDataset(dataset, i, value)
            prob = len(subDataset) / float(len(dataset))
            newEntropy += prob * calcShannonEnt(subDataset)
        infoGain = baseEntropy - newEntropy
        if infoGain > bestInfoGain:
            bestInfoGain = infoGain
            bestFeature = i
    return bestFeature


# mydata,labels = createDataset()
# bestFeature = chooseBestFeatureToSplit(mydata)
# print(mydata)
# print(bestFeature)


# 多数表决
# 处理了所有的特征后，类标签依然不是唯一的
# 使用多数表决来决定叶子节点的分类
def majorityCount(classList):
    classCount = {}
    for vote in classList:
        if vote not in classCount.keys():
            classCount[vote] = 0
        classCount[vote] += 1
    sortedClassCount = sorted(classCount.items(),
                              key=operator.itemgetter(1),
                              reverse=True)
    return sortedClassCount[0][0]


# 决策树
def createTree(dataset, labels):
    classList = [example[-1] for example in dataset]
    # 如果数据集只有一个分类，返回分类结果（类标签）
    if classList.count(classList[0]) == len(classList):
        return classList[0]
    # 数据集只有分类结果，没有特征，根据多数表决返回分类结果（类标签）
    if len(dataset[0]) == 1:
        return majorityCount(classList)
    # 寻找划分数据集的最佳特征
    bestFeat = chooseBestFeatureToSplit(dataset)
    bestFeatLabel = labels[bestFeat]
    myTree = {bestFeatLabel: {}}
    del (labels[bestFeat])
    # 划分数据集，并在划分的每个子集中创建分支
    featValues = [example[bestFeat] for example in dataset]
    uniqueVals = set(featValues)
    for value in uniqueVals:
        subLabels = labels[:]
        myTree[bestFeatLabel][value] = createTree(
            splitDataset(dataset, bestFeat, value), subLabels)
    return myTree


# mydata, labels = createDataset()
# mytree = createTree(mydata, labels)
# print(mydata)
# print(mytree)
# [[1, 1, 'yes'], [1, 1, 'yes'], [1, 0, 'no'], [0, 1, 'no'], [0, 1, 'no']]
# {'no surfacing': {0: 'no', 1: {'flippers': {0: 'no', 1: 'yes'}}}}


# 使用决策树
def classify(inputTree, featLabels, testVec):
    firstStr = list(inputTree.keys())[0]
    secondDict = inputTree[firstStr]
    featIndex = featLabels.index(firstStr)
    for key in secondDict.keys():
        if testVec[featIndex] == key:
            if type(secondDict[key]).__name__ == 'dict':
                classLabel = classify(secondDict[key], featLabels, testVec)
            else:
                classLabel = secondDict[key]
    return classLabel


# mydata, labels = createDataset()
# mytree = createTree(mydata, labels)
# print(classify(mytree, labels, [1, 1]))
# 程序报错ValueError: 'no surfacing' is not in list
# 因为createTree()函数中删除了最佳划分特征的标签 del(labels[bestFeat])

# mydata, labels = createDataset()
# mytree = createTree(mydata, labels)
# mydata, labels = createDataset()
# print(classify(mytree, labels, [1, 1]))


# 储存决策树
# pickle模块可以将小规模的数据存储在文件中，并在需要时读取出来
def storeTree(inputTree, filename):
    import pickle
    fw = open(filename, 'wb+')
    pickle.dump(inputTree, fw)
    fw.close()


def grabTree(filename):
    import pickle
    fr = open(filename, 'rb')
    return pickle.load(fr)


# mytree = {'no surfacing': {0: 'no', 1: {'flippers': {0: 'no', 1: 'yes'}}}}
# storeTree(mytree, 'C:/Users/Administrator/Desktop/classifier.txt')
# a = grabTree('C:/Users/Administrator/Desktop/classifier.txt')
# print(a)

# 实例：使用决策树预测隐形眼镜类型
f = open('D:\Coding\Py\Machine-Learning\Decision-Tree\lenses.txt')
lenses = [line.strip().split('\t') for line in f.readlines()]
lensesLabels = ['age', 'prescript', 'astigmatic', 'tearRate']
lensesTree = createTree(lenses, lensesLabels)
print(lenses)
print(lensesLabels)
print(lensesTree)
# 绘制决策树
import treePlotter

treePlotter.createPlot(lensesTree)