import time
import asyncio

async def func1(param:int):
    print(f"id={param} started at {time.strftime('%X')}")
    await asyncio.sleep(param) # 没有占用cpu，更适合异步的场景，比如磁盘IO，网络IO，请求IO等
    print(param)
    print(f"id={param} finished at {time.strftime('%X')}")


async def func2(param:int):
    print(f"id={param} started at {time.strftime('%X')}")
    time.sleep(param) # 占用了cpu
    print(param)
    print(f"id={param} finished at {time.strftime('%X')}")


async def func3(param:int):
    print(f"id={param} started at {time.strftime('%X')}")
    sum = 1
    for i in range(param * 10000000):
        sum += 1 # 占用了cpu
    print(param)
    print(f"id={param} finished at {time.strftime('%X')}")


async def do_work():
    async with asyncio.TaskGroup() as tg:
        for i in range(10):
            tg.create_task(func1(i))
        print(f"started at {time.strftime('%X')}")

    print(f"finished at {time.strftime('%X')}")
        
if __name__ == '__main__':
    asyncio.run(do_work())