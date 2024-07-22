from typing import Any

class meta(type):

    def __init__(self, class_name, class_bases, class_dic):
        print(class_name)


class A(metaclass = meta):

    def __init__(self, name) -> None:
        self.name = name
        print("init")

    def __call__(self, *args: Any, **kwds: Any) -> Any:
        print("call")
    
a = A(name="123")
print(a.name)

    