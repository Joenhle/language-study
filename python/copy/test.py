import copy


def test1():

    a = [1, 2, 3]
    b = a
    b.append(30) # b和a都是同一个对象的引用，这里底层都会改变
    print(a)

def test2():
    a = [1, 2, 3, [4, 5]]
    b = copy.copy(a) # 浅拷贝，对于引用对象也是拷贝的引用
    c = copy.deepcopy(a) # 深拷贝，会进行内存上的拷贝
    a[0] = 100
    a[3][0] = 100
    print(b)
    print(c)
    
# test1()
test2()