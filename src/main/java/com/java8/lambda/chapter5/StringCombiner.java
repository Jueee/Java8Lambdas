package com.java8.lambda.chapter5;

public class StringCombiner {

	private final String prefix;	// 前缀
    private final String suffix;	// 后缀
    private final String delim;		// 分隔符
    private final StringBuilder buIlder;

    public StringCombiner(String delim, String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
        this.buIlder = new StringBuilder();
    }

    /**
     * 	add 方法在内部其实将操作代理给一个 StringBuilder 对象
     * 	如果刚开始进行连接，则在最前面添加前缀，否则添加分隔符，然后再添加新的元素。
     * 	这里返回一个 StringCombiner 对象，因为这是传给 reduce 操作所需要的类型。
     *	@param word
     *	@return
     */
    public StringCombiner add (String word) {
        if(!this.areAtStart()) {
            this.buIlder.append(delim);
        }
        this.buIlder.append(word);

        return this;
    }

    public StringCombiner merge (StringCombiner other) {
        if(!other.equals(this)) {
            if(!other.areAtStart() && !this.areAtStart()){
                other.buIlder.insert(0, this.delim);
            }
            this.buIlder.append(other.buIlder);
        }
        return this;
    }

    @Override
    public String toString() {
        return prefix + buIlder.toString() + suffix;
    }

    private boolean areAtStart() {
        return buIlder.length() == 0;
    }
}
