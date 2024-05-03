#!/bin/bash

#проверка набора аргументов
if [[ $# -ne 2 ]]; then
	echo "Usage: $0 <input_dir> <output_dir>" 
	exit 1
fi

input_dir="$1"
output_dir="$2"

#опционально, удаление всех файлов в выходной дирректории
find "$output_dir" -type f -exec rm {} \;

echo "File in input_dir:"
find "$input_dir" -maxdepth 1 -type f -exec basename {} \;

echo -e "Dir in input_dir:"
find "$input_dir" -mindepth 1 -maxdepth 1 -type d -exec basename {} \;

echo -e "ALL files in input_dir:"
find "$input_dir" -type f -exec basename {} \;

#Copy files
find "$input_dir" -type_f | while IFS= read -r file; do
	#проверка скрытости
	if [[ "$(basename "$file")" == .* ]]; then
		echo "Skip hidden file: $file"
		continue
	fi

 	#проверка доступа
	if [[ ! -r "$file" ]]; then
		echo "Skip file without access: $file"
		continue
	fi

	#проверка является ли файл ссылкой
 	if [[ -L "$file" ]]; then
        	echo "Skip linkFile: $file"
        	continue
    	fi

 	#проверка на одинаковость имен и создание нового имени
	bn=$(basename "$file")
	dest="$output_dir$bn"
	count=1
	while [[ -e "$dest" ]]; do
		dest="$output_dir${bn%.*}$count.${bn##*.}" 
		count=$((count+1))
	done
	cp "$file" "$dest"
done
