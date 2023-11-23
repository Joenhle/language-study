package main

import (
	"bytes"
	"encoding/json"
	"sync"
	"testing"
)

type Student struct {
	Name   string
	Age    int32
	Remark [1024]byte
}

var jsonBuf, _ = json.Marshal(Student{Name: "hjh", Age: 24})

/**
demo1
从benchmark的结果可以看出，时间相差不大，因为序列化反射的时间占了大头，所以提升不大，但是可以看出来内存的占用率相差挺大的
*/
func BenchmarkUnmarshal(b *testing.B) {
	for i := 0; i < b.N; i++ {
		stu := &Student{}
		json.Unmarshal(jsonBuf, stu)
	}
}

func BenchmarkUnmarshalWithPool(b *testing.B) {
	var studentPool = sync.Pool{
		New: func() interface{} {
			return new(Student)
		},
	}
	for i := 0; i < b.N; i++ {
		stu := studentPool.Get().(*Student)
		json.Unmarshal(jsonBuf, stu)
		studentPool.Put(stu)
	}
}

var data = make([]byte, 10000)

/**
demo2
从benchmark的结果可以看出，时间相差
*/
func BenchmarkBufferWrite(b *testing.B) {
	for i := 0; i < b.N; i++ {
		var buf = bytes.Buffer{}
		buf.Write(data)
	}
}

func BenchmarkBufferWriteWithPool(b *testing.B) {
	var bytesBuffer = sync.Pool{
		New: func() interface{} {
			return new(bytes.Buffer)
		},
	}
	for i := 0; i < b.N; i++ {
		var buf = bytesBuffer.Get().(*bytes.Buffer)
		buf.Write(data)
		buf.Reset()
		bytesBuffer.Put(buf)
	}
}
