class Meta(type):
    def __call__(cls, *args, **kwargs):
        print(f"Creating instance of {cls.__name__} with arguments: {args}, {kwargs}")
        # 可以在这里添加实例化的自定义逻辑

        print(f"super is {super()}")

        instance = super().__call__(*args, **kwargs)
        instance.a = "123"
        print("1")
        return instance

class MyClass(metaclass=Meta):
    def __init__(self, value):
        print("2")
        self.value = value

# 创建 MyClass 的实例
obj = MyClass(10)
print(obj.value)
print(obj.a)